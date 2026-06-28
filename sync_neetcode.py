#!/usr/bin/env python3
import os
import sys
import shutil
import json
import re
import subprocess

# Paths
JAVA_PRACTICE_DIR = "/Users/suresh/Desktop/ShopWise/java-practice"
NEETCODE_SUBMISSIONS_DIR = "/Users/suresh/Desktop/ShopWise/neetcode-submissions"
PROBLEM_SITE_DATA_PATH = os.path.join(NEETCODE_SUBMISSIONS_DIR, "problemSiteData.json")
NC150_LIST_PATH = os.path.join(NEETCODE_SUBMISSIONS_DIR, "neetcode-150-list.json")

def normalize(name):
    # remove extension and keep only lowercase alphanumeric characters
    name = os.path.splitext(name)[0]
    return re.sub(r'[^a-z0-9]', '', name.lower())

# Manual mappings for known custom abbreviations/names in java-practice
MANUAL_MAPPINGS = {
    'threesum': '3Sum',
    'maxprofit': 'Best Time to Buy And Sell Stock',
    'longestsubstring': 'Longest Substring Without Repeating Characters',
    'minsizesubarray': 'Minimum Size Subarray Sum',
    'minimumelement': 'Find Minimum in Rotated Sorted Array',
    'maxsubarraysum': 'Maximum Subarray',
    'maxsubarrayproduct': 'Maximum Product Subarray',
    'removeduplicates': 'Remove Duplicates from Sorted Array',
    'squareofsortedarrays': 'Squares of a Sorted Array',
    'pascalstriangle': 'Pascal\'s Triangle',
    'longestpalindrome': 'Longest Palindromic Substring',
    'searchrange': 'Find First and Last Position of Element in Sorted Array',
    'rotatearraybyk': 'Rotate Array',
    'mergesortedarrays': 'Merge Sorted Array',
    'productexceptself': 'Product of Array Except Self',
    'continoussubarraysum': 'Continuous Subarray Sum',
    'partitionequalssubsetsum': 'Partition Equal Subset Sum',
    'deletemiddlenode': 'Delete the Middle Node of a Linked List',
    'pivotindex': 'Find Pivot Index',
    'firstuniquecharstring': 'First Unique Character in a String',
    'moveallzeros': 'Move Zeroes',
}

def load_neetcode_database():
    # 1. Load custom NeetCode 150 slugs if available
    custom_slugs = {}
    if os.path.exists(NC150_LIST_PATH):
        try:
            with open(NC150_LIST_PATH) as f:
                nc150 = json.load(f)
            for cat, probs in nc150.items():
                for p_name, p_info in probs.items():
                    nurl = p_info.get('nurl', '')
                    slug = nurl.split('/')[-1].split('?')[0] if '/' in nurl else ''
                    if slug:
                        custom_slugs[p_name] = slug
        except Exception as e:
            print(f"Warning: Could not parse neetcode-150-list.json: {e}")

    # 2. Load problem database
    problems_db = {}
    if os.path.exists(PROBLEM_SITE_DATA_PATH):
        try:
            with open(PROBLEM_SITE_DATA_PATH) as f:
                site_data = json.load(f)
            for p in site_data:
                title = p['problem']
                slug = custom_slugs.get(title, p['link'].rstrip('/'))
                norm_title = normalize(title)
                problems_db[norm_title] = {
                    'title': title,
                    'slug': slug,
                    'pattern': p['pattern']
                }
        except Exception as e:
            print(f"Warning: Could not parse problemSiteData.json: {e}")
    
    return problems_db

def find_java_files():
    java_files = []
    # Scan arrays and leetcode directories
    dirs_to_scan = [
        os.path.join(JAVA_PRACTICE_DIR, "dsa", "leetcode"),
        os.path.join(JAVA_PRACTICE_DIR, "dsa", "arrays")
    ]
    for d in dirs_to_scan:
        if os.path.exists(d):
            for file in os.listdir(d):
                if file.endswith(".java"):
                    java_files.append(os.path.join(d, file))
    return java_files

def main():
    print("Starting NeetCode solutions sync...")
    problems_db = load_neetcode_database()
    java_files = find_java_files()
    
    synced_count = 0
    
    for filepath in java_files:
        filename = os.path.basename(filepath)
        norm_name = normalize(filename)
        
        matched_problem = None
        
        # 1. Manual check
        if norm_name in MANUAL_MAPPINGS:
            target_title = MANUAL_MAPPINGS[norm_name]
            norm_target = normalize(target_title)
            matched_problem = problems_db.get(norm_target)
            
        # 2. Exact match in database
        if not matched_problem:
            matched_problem = problems_db.get(norm_name)
            
        # 3. Substring match
        if not matched_problem:
            for norm_title, p_info in problems_db.items():
                if norm_name in norm_title or norm_title in norm_name:
                    matched_problem = p_info
                    break
                    
        if matched_problem:
            slug = matched_problem['slug']
            # NeetCode folder structure: Data Structures & Algorithms/<slug>/submission-0.java
            target_dir = os.path.join(NEETCODE_SUBMISSIONS_DIR, "Data Structures & Algorithms", slug)
            os.makedirs(target_dir, exist_ok=True)
            target_path = os.path.join(target_dir, "submission-0.java")
            
            # Check if file needs updating
            copy_needed = True
            if os.path.exists(target_path):
                with open(filepath, 'rb') as f1, open(target_path, 'rb') as f2:
                    if f1.read() == f2.read():
                        copy_needed = False
            
            if copy_needed:
                shutil.copy2(filepath, target_path)
                print(f"Synced: {filename} -> Data Structures & Algorithms/{slug}/submission-0.java")
                synced_count += 1
        else:
            # Skip unmatched files silently
            pass

    print(f"Total new or updated files copied: {synced_count}")

    # Check for changes in target repo and commit/push
    print("Checking for changes in neetcode-submissions...")
    subprocess.run(["git", "add", "."], cwd=NEETCODE_SUBMISSIONS_DIR)
    
    # Check if there are changes to commit
    status_proc = subprocess.run(
        ["git", "status", "--porcelain"], 
        cwd=NEETCODE_SUBMISSIONS_DIR, 
        capture_output=True, 
        text=True
    )
    
    if status_proc.stdout.strip():
        print("Changes detected in neetcode-submissions. Committing...")
        subprocess.run(
            ["git", "commit", "--no-gpg-sign", "-m", "Sync solution(s) from java-practice"], 
            cwd=NEETCODE_SUBMISSIONS_DIR
        )
        print("Pushing to GitHub...")
        subprocess.run(["git", "push"], cwd=NEETCODE_SUBMISSIONS_DIR)
        print("Push completed successfully!")
    else:
        print("neetcode-submissions repository is already up to date.")

if __name__ == "__main__":
    main()

import re

filename = "README.md"

# Read README
with open(filename, "r", encoding="utf-8") as file:
    content = file.read()

# Match:
# https://github.com/ronaldo-28/dataStructesAndAlgolithms/tree/master/<problem-folder>
pattern = re.compile(
    r"https://github\.com/ronaldo-28/dataStructesAndAlgolithms/tree/master/"
    r"((?P<number>"
    r"[1-9]\d{0,2}|1000|"
    r"1\d{3}|2000|"
    r"2\d{3}|3000|"
    r"3\d{3}|4000"
    r")-[^)\s]+)"
)

def replace_link(match):
    problem_folder = match.group(1)
    problem_number = int(match.group("number"))

    if 1 <= problem_number <= 1000:
        parent_folder = "1-1000"
    elif 1001 <= problem_number <= 2000:
        parent_folder = "1001-2000"
    elif 2001 <= problem_number <= 3000:
        parent_folder = "2001-3000"
    elif 3001 <= problem_number <= 4000:
        parent_folder = "3001-4000"
    else:
        return match.group(0)

    return (
        "https://github.com/ronaldo-28/"
        "data-structures-algorithms-java-cpp/tree/main/"
        f"{parent_folder}/{problem_folder}"
    )

# Replace links
new_content, number_of_changes = pattern.subn(replace_link, content)

# Save README
with open(filename, "w", encoding="utf-8") as file:
    file.write(new_content)

print(f"Success! Updated {number_of_changes} links directly inside {filename}.")

use crate::util::{load_file, print_result};

pub fn run() {
    part1();
    part2();
}


fn part1() {
    let content = load_file(5);
    let lines = content.lines();

    let mut nice_line_counter = 0;
    for line in lines {
        if is_nice_part1(line) {
            nice_line_counter += 1;
        }
    }

    // 238
    print_result(5, 1, nice_line_counter);
}


fn is_nice_part1(line: &str) -> bool {
    let mut has_double_char = false;
    let mut does_not_contain_bad_pairs = true;

    let mut vowel_counter = 0;
    let mut previous_char = '1';
    for char in line.chars() {
        // Rule 1
        // At least 3 vowels  aeiou
        if char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u' {
            vowel_counter += 1;
        }

        // Rule 2
        // At least one letter that appears twice
        if previous_char == char {
            has_double_char = true;
        }

        // Rule 3
        // Does not contain ab, cd, pq, or xy
        let pair = format!("{}{}", previous_char, char);
        if [String::from("ab"), String::from("cd"), String::from("pq"), String::from("xy")].contains(&pair) {
            does_not_contain_bad_pairs = false
        }

        previous_char = char;
    }

    return vowel_counter >= 3 && has_double_char && does_not_contain_bad_pairs;
}

fn part2() {
    let content = load_file(5);
    let lines = content.lines();

    let mut nice_line_counter = 0;
    for line in lines {
        if is_nice_part2(line) == (true, true) {
            nice_line_counter += 1;
        }
    }
    // Not 68
    print_result(5, 2, nice_line_counter);
}

fn is_nice_part2(line: &str) -> (bool, bool) {
    let chars = line.chars();

    let mut has_not_overlapping_pair = false;
    let mut has_repeating_char = false;

    let mut pairs: Vec<String> = Vec::new();
    let mut previous_pair = String::from(" ");
    let mut previous_previous_pair = String::from(" ");

    let mut previous_char: char = ' ';
    let mut previous_previous_char: char = ' ';
    for char in chars {
        let pair = format!("{}{}", previous_char, char);

        // Rule 1
        if pairs.contains(&pair) {
            if previous_previous_pair == pair {
                has_not_overlapping_pair = true
            }
            if pairs.last().unwrap() != &pair {
                has_not_overlapping_pair = true
            }
        }

        // Rule 2
        if previous_previous_char == char {
            has_repeating_char = true;
        }

        previous_previous_pair = previous_pair;
        previous_pair = pair.clone();
        pairs.push(pair);

        previous_previous_char = previous_char;
        previous_char = char;
    }

    return (has_not_overlapping_pair, has_repeating_char);
}


#[cfg(test)]
mod tests {
    use crate::day5::{is_nice_part1, is_nice_part2};

    #[test]
    fn is_nice_part1_works() {
        assert_eq!(is_nice_part1("ugknbfddgicrmopn"), true);
        assert_eq!(is_nice_part1("aaa"), true);

        // No double letter
        assert_eq!(is_nice_part1("jchzalrnumimnmhp"), false);
        // contains bad pair
        assert_eq!(is_nice_part1("haegwjzuvuyypxyu"), false);
        // only one vowel
        assert_eq!(is_nice_part1("dvszwmarrgswjxmb"), false);
    }


    #[test]
    fn is_nice_part2_works() {
        assert_eq!(is_nice_part2("qjhvhtzxzqqjkmpb"), (true, true));
        assert_eq!(is_nice_part2("xxyxx"), (true, true));

        // pairs
        assert_eq!(is_nice_part2("xyxy"), (true, true));
        assert_eq!(is_nice_part2("aabcdefgaa"), (true, false));
        assert_eq!(is_nice_part2("aaa"), (false, true));
        assert_eq!(is_nice_part2("aaaa"), (true, true));
        assert_eq!(is_nice_part2("ieodomkazucvgmuy"), (false, true));

        // double
        assert_eq!(is_nice_part2("xyx"), (false, true));
        assert_eq!(is_nice_part2("abcdefeghi"), (false, true));
        assert_eq!(is_nice_part2("aaa"), (false, true));
        assert_eq!(is_nice_part2("uurcxstgmygtbstg"), (true, false));
    }
}
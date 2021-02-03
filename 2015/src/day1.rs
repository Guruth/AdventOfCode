use crate::util::{load_file, print_result};

pub fn run() {
    part1();
    part2();
}


fn part1() {
    let floor_map_string = load_file(1);
    let floor_map: Vec<char> = floor_map_string.chars().collect();

    let mut current_floor: i32 = 0;

    for direction in floor_map {
        match direction {
            '(' => current_floor += 1,
            ')' => current_floor -= 1,
            _ => {}
        }
    }
    // Result: 280
    print_result(1, 1, current_floor as i64)
}

fn part2() {
    let floor_map_string = load_file(1);
    let floor_map: Vec<char> = floor_map_string.chars().collect();

    let mut current_floor = 1;
    for (index, direction) in floor_map.iter().enumerate() {
        match direction {
            '(' => current_floor += 1,
            ')' => current_floor -= 1,
            _ => {}
        }
        if current_floor == -1 {
            // Result 1797
            print_result(1, 2, index as i64);
            break;
        }
    }
}

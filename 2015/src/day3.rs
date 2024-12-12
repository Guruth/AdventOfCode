use std::collections::HashMap;

use crate::util::{load_file, print_result};

pub fn run() {
    part1();
    part2();
}


fn part1() {
    let navigation: Vec<char> = load_file(3).chars().collect();

    let mut current_position = (0, 0);
    let mut map: HashMap<(i32, i32), i32> = HashMap::new();
    map.insert(current_position, 1);

    for next_direction in navigation {
        current_position = navigate(current_position, next_direction);
        let times_visited = map.entry(current_position).or_insert(0);
        *times_visited += 1;
    }

    // 2565
    print_result(3, 1, map.len() as i64)
}

fn part2() {
    let navigation_string = load_file(3);
    let navigation = navigation_string.chars().enumerate();

    let mut santa_current_position = (0, 0);
    let mut robot_current_position = (0, 0);

    let mut map: HashMap<(i32, i32), i32> = HashMap::new();
    map.insert((0, 0), 2);


    for (index, next_direction) in navigation {
        if index % 2 == 0 {
            santa_current_position = navigate(santa_current_position, next_direction);
            let times_visited = map.entry(santa_current_position).or_insert(0);
            *times_visited += 1;
        } else {
            robot_current_position = navigate(robot_current_position, next_direction);
            let times_visited = map.entry(robot_current_position).or_insert(0);
            *times_visited += 1;
        }
    }

    // 2639
    print_result(3, 2, map.len() as i64)
}

fn navigate(current_coordinate: (i32, i32), next_direction: char) -> (i32, i32) {
    let mut x = current_coordinate.0;
    let mut y = current_coordinate.1;
    match next_direction {
        '>' => { x += 1; }
        '<' => { x -= 1 }
        'v' => { y -= 1 }
        '^' => { y += 1 }
        _ => {
            println!("Unknown Direction!");
        }
    }
    return (x, y);
}
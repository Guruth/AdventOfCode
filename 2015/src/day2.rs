use std::cmp;

use crate::util::{load_file, print_result};

pub fn run() {
    part1();
    part2();
}

fn part1() {
    let content = load_file("day2");
    let lines = content
        .lines();

    let mut total_area: i64 = 0;

    for line in lines {
        let sides: Vec<i64> = line.split("x").map(|s| {
            s.trim().parse().unwrap()
        }).collect();

        let first_area = sides[0] * sides[1];
        let second_area = sides[1] * sides[2];
        let third_area = sides[0] * sides[2];
        let smallest_area = cmp::min(third_area, cmp::min(first_area, second_area));

        total_area += smallest_area + (2 * first_area) + (2 * second_area) + (2 * third_area)
    }

    print_result(2, 1, total_area)
}

fn part2() {
    let content = load_file("day2");
    let lines = content.lines();

    let mut total_length: i64 = 0;

    for line in lines {
        let sides: Vec<i64> = line.split("x").map(|s| {
            s.trim().parse().unwrap()
        }).collect();

        let first_circumference = (2 * sides[0]) + (2 * sides[1]);
        let second_circumference = (2 * sides[1]) + (2 * sides[2]);
        let third_circumference = (2 * sides[0]) + (2 * sides[2]);
        let smallest_circumference = cmp::min(first_circumference, cmp::min(second_circumference, third_circumference));

        total_length += smallest_circumference + (sides[0] * sides[1] * sides[2]);
    }

    print_result(2, 2, total_length)
}
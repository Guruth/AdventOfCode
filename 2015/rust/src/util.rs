use std::fs::read_to_string;

pub fn load_file(day_number: i8) -> String {
    println!("Loading input for day {}", day_number);
    return read_to_string(format!("./data/day{}.txt", day_number))
        .expect("Could not read file!");
}


pub fn print_result(day: i8, part: i8, result: i64) {
    println!("Result of Day {} Part {} is {}", day, part, result);
}
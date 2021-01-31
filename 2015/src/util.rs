use std::fs::read_to_string;

pub fn load_file(file_name: &str) -> String {
    println!("Loading file: {}", file_name);
    return read_to_string(format!("./data/{}.txt", file_name))
        .expect("Could not read file!");
}



pub fn print_result(day: i8, part: i8, result: i64) {
    println!("Result of Day {} Part {} is {}", day, part, result);
}
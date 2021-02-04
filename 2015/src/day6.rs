use std::str::FromStr;

use crate::util::{load_file, print_result};

pub fn run() {
    part1();
    part2();
}

struct LightInstruction {
    action: LightAction,
    from: (usize, usize),
    to: (usize, usize),
}

#[derive(Clone, Copy)]
enum LightAction {
    TurnOn,
    TurnOff,
    Toggle,
}

fn part1() {
    let content = load_file(6);
    let lines = content.lines();

    let mut light_matrix: Vec<Vec<bool>> = vec![vec![false; 1000]; 1000];

    for line in lines {
        let instruction = parse_instruction(line);

        for (y, matrix_line) in light_matrix.iter_mut().enumerate() {
            for (x, element) in matrix_line.iter_mut().enumerate() {
                if x >= instruction.from.0 && x <= instruction.to.0 && y >= instruction.from.1 && y <= instruction.to.1 {
                    match instruction.action {
                        LightAction::TurnOn => { *element = true }
                        LightAction::TurnOff => { *element = false }
                        LightAction::Toggle => { *element = !*element }
                    }
                }
            }
        }
    }

    let lit_lights = light_matrix.iter().flatten().filter(|lit| **lit).count();
    print_result(6, 1, lit_lights as i64)
}


fn part2() {
    let content = load_file(6);
    let lines = content.lines();

    let mut light_matrix: Vec<Vec<u32>> = vec![vec![0; 1000]; 1000];

    for line in lines {
        let instruction = parse_instruction(line);

        for (y, matrix_line) in light_matrix.iter_mut().enumerate() {
            for (x, element) in matrix_line.iter_mut().enumerate() {
                if x >= instruction.from.0 && x <= instruction.to.0 && y >= instruction.from.1 && y <= instruction.to.1 {
                    match instruction.action {
                        LightAction::TurnOn => { *element += 1 }
                        LightAction::TurnOff => { if *element > 0 { *element -= 1 } }
                        LightAction::Toggle => { *element += 2 }
                    }
                }
            }
        }
    }

    let lit_lights: u32 = light_matrix.iter().flatten().sum();
    print_result(6, 2, lit_lights as i64)
}

fn parse_instruction(line: &str) -> LightInstruction {
    let split_line: Vec<&str> = line.split(" ").collect();
    return match split_line[0] {
        "turn" => {
            match split_line[1] {
                "on" => {
                    LightInstruction {
                        action: LightAction::TurnOn,
                        from: parse_coordinates(split_line[2]),
                        to: parse_coordinates(split_line[4]),
                    }
                }
                "off" => {
                    LightInstruction {
                        action: LightAction::TurnOff,
                        from: parse_coordinates(split_line[2]),
                        to: parse_coordinates(split_line[4]),
                    }
                }
                _ => {
                    panic!("Unknown Instruction!")
                }
            }
        }
        "toggle" => {
            LightInstruction {
                action: LightAction::Toggle,
                from: parse_coordinates(split_line[1]),
                to: parse_coordinates(split_line[3]),
            }
        }
        _ => {
            panic!("Unknown Instruction!")
        }
    };
}

fn parse_coordinates(coordinates: &str) -> (usize, usize) {
    let split_coordinates: Vec<&str> = coordinates.split(",").collect();
    return (usize::from_str(split_coordinates[0]).unwrap(), usize::from_str(split_coordinates[1]).unwrap());
}


#[cfg(test)]
mod tests {}
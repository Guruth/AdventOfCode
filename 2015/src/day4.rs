use crate::util::{ print_result};

pub fn run() {
    part1();
    part2();
}


fn part1() {
    let secret = "yzbqklnj";
    let mut result_number = 0;
    loop {
        let digest = md5::compute(format!("{}{}", secret, result_number).into_bytes());
        if format!("{:x}", digest).starts_with("00000") {
            break;
        }
        result_number += 1;
    }

    // 282749
    print_result(4, 1, result_number)
}

fn part2() {
    let secret = "yzbqklnj";
    let mut result_number = 0;
    loop {
        let digest = md5::compute(format!("{}{}", secret, result_number).into_bytes());
        if format!("{:x}", digest).starts_with("000000") {
            break;
        }
        result_number += 1;
    }

    // 9962624
    print_result(4, 2, result_number)
}
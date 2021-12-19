use std::fmt::format;

pub fn file_loader(year: u16, day: u8) -> String {
    let x = format!("{}{}{}{}{}", "assets/y", &year.to_string(), "/Day", format!("{:0>2}", day.to_string()), ".txt");
    println!("{}", x);
    std::fs::read_to_string(x).expect("Something went wrong reading a file!")
}
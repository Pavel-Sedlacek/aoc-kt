use crate::lib::utils::file_loader::file_loader;
use crate::lib::years::y2021::day01::Day01;
use crate::lib::years::y2021::day02::Day02;
use crate::lib::years::y2021::day03::Day03;
use crate::lib::years::y2021::day04::Day04;
use crate::lib::years::y2021::day05::Day05;
use crate::Year;

pub mod y2021;
pub mod y2020;

pub fn run() {
    let years = vec![
        Year::new(2021, vec![
            Box::new(Day01 { file: file_loader(2021, 1), desc: "consequent changes in depth values".to_string() }),
            Box::new(Day02 { file: file_loader(2021, 2), desc: "submarine navigation".to_string() }),
            Box::new(Day03 { file: file_loader(2021, 3), desc: "common bits".to_string() }),
            Box::new(Day04 { file: file_loader(2021, 4), desc: "bingo boards".to_string() }),
            Box::new(Day05 { file: file_loader(2021, 5), desc: "xxx".to_string() }),
        ])
    ];

    for x in years {
        x.run_all()
    }
}


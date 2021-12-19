use std::error::Error;
use std::fmt::Display;
use std::time::Instant;

use crate::lib::utils::metrics::timer;
use crate::lib::utils::tday::TDay;

impl Year {
    pub fn new(year: u16, days: Vec<Box<dyn TDay>>) -> Self {
        Year {
            year,
            days,
        }
    }

    pub fn run_all(&self) -> () {
        println!("{}", self.year);
        let mut c_day = 0;
        for day in &self.days {
            print!(" Day: {} ->  ", c_day % 25 + 1);

            let p1 = timer(|| { day.part_one() });
            let p2 = timer(|| { day.part_two() });
            println!("{}µs | {}µs", p1.0, p2.0);

            println!("  {}", day.desc());

            println!("   part one: {}", p1.1);
            println!("   part two: {}", p2.1);
        }
    }
}

pub struct Year {
    year: u16,
    days: Vec<Box<dyn TDay>>,
}

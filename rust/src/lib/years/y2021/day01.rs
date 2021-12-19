use std::borrow::Borrow;
use std::fmt::{Display, format};

use crate::lib::utils::data_convertors::from_str::str_to_unsigned_int;
use crate::lib::utils::tday::TDay;
use crate::lib::utils::utils::changes;

pub struct Day01 {
    pub file: String,
    pub desc: String,
}

impl TDay for Day01 {
    fn desc(&self) -> String {
        (&self.desc).to_string()
    }

    fn part_one(&self) -> String {
        format!("{}", changes(self.file.lines().map(|x| { str_to_unsigned_int(x) }).collect(), true))
    }

    fn part_two(&self) -> String {
        let z: Vec<u64> = self.file.lines().map(|x| { str_to_unsigned_int(x) }).collect();

        format!("{}", changes(z[0..z.len()].windows(3).map(|x| { x.iter().sum() }).collect(), true))
    }
}


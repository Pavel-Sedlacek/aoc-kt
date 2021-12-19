use crate::lib::utils::data_convertors::from_str::{str_to_signed_int, str_to_unsigned_int};
use crate::lib::utils::tday::TDay;

pub struct Day04 {
    pub file: String,
    pub desc: String,
}

impl TDay for Day04 {
    fn desc(&self) -> String {
        (&self.desc).to_string()
    }

    fn part_one(&self) -> String {
        let x: Vec<&str> = self.file.split("\n\n").collect();
        let board: Vec<BingoBoard> = x[1..x.len()].iter().map(|&x| {
            x.trim().split("\n").map(|i| {
                i.trim().split_whitespace().map(|j| {
                    str_to_signed_int(j.trim())
                }).collect()
            }).collect()
        }).map(|x| {
            BingoBoard { board: x }
        }).collect();

        format!("{}", 2)
    }

    fn part_two(&self) -> String {
        format!("{}", 2)
    }
}

struct BingoBoard {
    pub board: Vec<Vec<i64>>,
}

impl BingoBoard {
    fn is_winning(&self) -> bool {
        'outer: for i in 0..self.board[0].len() {
            for row in self.board.iter() {
                if row[i] != -1 {
                    continue 'outer;
                }
            }
            return true;
        }
        return self.board.iter().any(|row| { row.iter().all(|element| { *element == -1 }) });
    }

    fn flip(&mut self, number: i64) {
        for mut x in self.board.clone() {
            let index = x.iter().position(|&r| { r == number });
            if index.is_some() {
                x[index.unwrap()] = -1
            }
        }
    }

    fn sum(&self) -> i64 {
        self.board.iter().map(|x| {
            let z: Vec<i64> = x.iter().filter(|&it| { *it != -1 }).copied().collect();
            let a: i64 = z.iter().sum();
            a
        }).sum()
    }
}

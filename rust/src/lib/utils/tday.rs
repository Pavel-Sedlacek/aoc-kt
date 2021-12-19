use std::fmt::Display;

pub trait TDay {
    fn desc(&self) -> String;
    fn part_one(&self) -> String;
    fn part_two(&self) -> String;
}
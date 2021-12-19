use crate::lib::utils::tday::TDay;

pub struct Day02 {
    pub file: String,
    pub desc: String,
}

impl TDay for Day02 {
    fn desc(&self) -> String {
        (&self.desc).to_string()
    }

    fn part_one(&self) -> String {
        format!("{}", 2)
    }

    fn part_two(&self) -> String {
        format!("{}", 2)
    }
}
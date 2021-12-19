pub fn str_to_unsigned_int(value: &str) -> u64 {
    value.parse().expect(&format!("String: *{}* cannot be converted into u64", value))
}

pub fn str_to_signed_int(value: &str) -> i64 {
    value.parse().expect(&format!("String: *{}* cannot be converted into i64", value))
}
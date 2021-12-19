use std::time::Instant;

pub fn timer<T>(fnc: T) -> (u128, String) where T: Fn() -> String {
    let now = Instant::now();
    let f = fnc();
    return ((Instant::now() - now).as_micros(), f);
}
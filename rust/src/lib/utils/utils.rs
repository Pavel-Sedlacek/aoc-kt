pub fn changes(arr: Vec<u64>, incremental: bool) -> u64 {
    let mut acc = 0;

    for x in 1..arr.len() {
        acc += if arr[x] > arr[x - 1] { if incremental { 1 } else { 0 } } else { if incremental { 0 } else { 1 } }
    };

    acc
}
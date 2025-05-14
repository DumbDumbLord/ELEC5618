log_file = 'markov_log.csv'

# Dictionary structure: {start_state: [list of end_states]}
start = {}

with open(log_file, 'r') as f:
    for line in f:
        parts = line.strip().split(",")
        if len(parts) != 5:
            continue
        from_state = parts[2]
        to_state = parts[4]

        if from_state in start:
            start[from_state].append(to_state)
        else:
            start[from_state] = [to_state]

# Print transition probabilities
print("Transition Probabilities:\n")
for key in start:
    ends = start[key]
    searched = []
    for end in ends:
        if end not in searched:
            prob = ends.count(end) / len(ends)
            print(f"{key} => {end} : {prob:.4f}")
            searched.append(end)

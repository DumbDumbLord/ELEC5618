# analyze_markov_chain.py
from collections import defaultdict

log_file = r'BIN\node_fsm.log' 

transition_counts = defaultdict(lambda: defaultdict(int))
state_counts = defaultdict(int)

with open(log_file, encoding='utf-8') as f:
    for line in f:
        parts = line.strip().split(',')
        # 跳过空行或格式不对的行
        if len(parts) < 5:
            continue
        # 取 from_state 和 to_state
        from_state = parts[2]
        to_state = parts[4]
        transition_counts[from_state][to_state] += 1
        state_counts[from_state] += 1

print("From_State,To_State,Count,Probability")
for from_state, to_states in transition_counts.items():
    total = state_counts[from_state]
    for to_state, count in to_states.items():
        prob = count / total if total > 0 else 0
        print(f"{from_state},{to_state},{count},{prob:.4f}")
log_file = 'MarkovLog.txt' 

# open file and sort movement of states in dictionary with start as key
start = {}
with open(log_file) as f:
    for line in f:
        path = line.split(",")
        if path[0] in start:
            start[path[0]].append(path[1])
        else:
            start[path[0]] = [path[1]]

# print results
print(start)
for key in start:
    ends = start[key]
    searched = []
    for end in ends:
        if end not in searched:
            print(key, "=>", end, ": {:.4f}".format(ends.count(end)/len(ends)))

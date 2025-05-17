import sys
data = sys.stdin.read().strip().split()
if not data: sys.exit(0)
n = int(data[0])
print(sum(range(1, n+1)))
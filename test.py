import sys
import numpy as np

def sum(v1, v2):
    result = int(v1) + int(v2)
    print(result)
#     print(np.arange(10))

def main(argv):
    sum(argv[1], argv[2])

if __name__ == "__main__":
    main(sys.argv)
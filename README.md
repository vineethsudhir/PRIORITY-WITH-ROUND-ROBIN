# PRIORITY-WITH-ROUND-ROBIN
Preemptive Process scheduling. Round robin with priority

## Problem Statement

Assume that Pa, Pb, and Pc are three distinguished programs. Assume processes executing Pa have a priority of 2, processes executing Pb have a priority of 3, and the processes executing Pb have a priority of 1. In order to complete their execution, Pa needs 10 ticks, Pb needs 7 ticks, and Pc needs 5 ticks. Assume that both Pa and Pb will fork a new process every 3 ticks of execution, and Pa forks new processes which execute Pb while Pb forks new processes which execute Pc. Assume that two processes P1 and P2 have arrived before time t=0. P1 is executing Pa and P2 is executing Pb. Implement this scenario illustrating the scheduling of these processes if a priority with round robin scheduling is used. Assume That a time quantum of 4 is used. Also assume that smaller number means higher priority. The program must print the gantt chart with the correct intervals.

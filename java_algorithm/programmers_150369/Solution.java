package programmers_150369;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int idx = n - 1;
        while (idx >= 0) {
            if (deliveries[idx] == 0 && pickups[idx] == 0) {
                idx--;
            } else {
                break;
            }
        }

        while (idx >= 0) {
            int deliverIdx = idx;
            int pickIdx = idx;
            int deliverBox = 0;
            int pickBox = 0;

            while (deliverBox < cap) {
                if (deliverIdx < 0) {
                    break;
                }
                if (deliveries[deliverIdx] == 0) {
                    deliverIdx--;
                    continue;
                }

                if (deliverBox + deliveries[deliverIdx] <= cap) {
                    deliverBox += deliveries[deliverIdx];
                    deliveries[deliverIdx--] = 0;

                    while (deliverIdx >= 0 && deliveries[deliverIdx] == 0) {
                        deliverIdx--;
                    }
                } else {
                    deliveries[deliverIdx] -= cap - deliverBox;
                    deliverBox = cap;
                }
            }

            while (pickBox < cap) {
                if (pickIdx < 0) {
                    break;
                }
                if (pickups[pickIdx] == 0) {
                    pickIdx--;
                    continue;
                }

                if (pickBox + pickups[pickIdx] <= cap) {
                    pickBox += pickups[pickIdx];
                    pickups[pickIdx--] = 0;

                    while (pickIdx >= 0 && pickups[pickIdx] == 0) {
                        pickIdx--;
                    }
                } else {
                    pickups[pickIdx] -= cap - pickBox;
                    pickBox = cap;
                }
            }

            answer += (idx + 1) * 2;
            idx = Math.max(deliverIdx, pickIdx);
        }

        return answer;
    }
}
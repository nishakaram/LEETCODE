class Solution {
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        char dir = 'N';

        for(int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);

            if(instruction == 'G') {
                if(dir == 'N') {
                    y++;
                }
                else if(dir == 'S') {
                    y--;
                }
                else if(dir == 'E') {
                    x++;
                }
                else {
                    x--;
                }
            }
            else if(instruction == 'L') {
                if(dir == 'N') {
                    dir = 'W';
                }
                else if(dir == 'W') {
                    dir = 'S';
                }
                else if(dir == 'S') {
                    dir = 'E';
                }
                else {
                    dir = 'N';
                }
            }
            else if(instruction == 'R') {
                if(dir == 'N') {
                    dir = 'E';
                }
                else if(dir == 'E') {
                    dir = 'S';
                }
                else if(dir == 'S') {
                    dir = 'W';
                }
                else {
                    dir = 'N';
                }
            }
        }
        if((x == 0 && y == 0) || dir != 'N') {
            return true;
        }
        return false;
    }
}
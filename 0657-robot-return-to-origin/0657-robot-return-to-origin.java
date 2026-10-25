class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for(int i = 0; i < moves.length(); i++) {
            char dir = moves.charAt(i);
            //Down
            if(dir == 'D') {
                y--;
            }
            else if(dir == 'U') {
                y++;
            }
            else if(dir == 'R') {
                x++;
            }
            else {
                x--;
            }
        }
        if(x == 0 && y == 0) {
            return true;
        }
        return false;
    }
}
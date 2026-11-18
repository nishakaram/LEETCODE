class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return findDepth(root, x, 0) == findDepth(root, y, 0) &&
               !areSiblings(root, x, y);
    }

    private int findDepth(TreeNode root, int val, int depth) {
        if (root == null) {
            return -1;
        }

        if (root.val == val) {
            return depth;
        }

        int left = findDepth(root.left, val, depth + 1);

        if (left != -1) {
            return left;
        }

        return findDepth(root.right, val, depth + 1);
    }

    private boolean areSiblings(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        if (root.left != null && root.right != null) {
            if ((root.left.val == x && root.right.val == y) ||
                (root.left.val == y && root.right.val == x)) {
                return true;
            }
        }

        return areSiblings(root.left, x, y) ||
               areSiblings(root.right, x, y);
    }
}
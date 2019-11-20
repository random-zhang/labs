package net;

public class RBTree<T extends Comparable<T>> {
    private final RBTreeNode<T> root;    //node number
    private java.util.concurrent.atomic.AtomicLong size =
            new java.util.concurrent.atomic.AtomicLong(0);   //in overwrite mode,all node's value can not  has same    value
    //in non-overwrite mode,node can have same value, suggest don't use non-overwrite mode.
    private volatile boolean overrideMode = true;

    public RBTree() {
        this.root = new RBTreeNode<T>();
    }

    public RBTree(boolean overrideMode) {
        this();
        this.overrideMode = overrideMode;
    }

    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    public long getSize() {
        return size.get();
    }

    private RBTreeNode<T> getRoot() {
        return root.getLeft();
    }

    public T addNode(T value) {
        RBTreeNode<T> t = new RBTreeNode<T>(value);
        return addNode(t);
    }

    private T addNode(RBTreeNode<T> node) {
        //初始化
        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);
        setParent(node, null);//
        if (root.getLeft() == null) {
            root.setLeft(node);            //root node is black
            node.setRed(false);
            size.incrementAndGet();
        } else {
            RBTreeNode<T> x = findParentNode(node);
            int cmp = x.getValue().compareTo(node.getValue());
            if (this.overrideMode && cmp == 0) {
                T v = x.getValue();
                x.setValue(node.getValue());
                return v;
            } else if (cmp == 0) {                //value exists,ignore this node
                return x.getValue();
            }

            setParent(node, x);
            if (cmp > 0) {
                x.setLeft(node);
            } else {
                x.setRight(node);
            }

            fixInsert(node);
            size.incrementAndGet();
        }
        return null;
    }

    public T find(T value) {
        RBTreeNode<T> dataRoot = getRoot();
        while (dataRoot != null) {
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0) {
                dataRoot = dataRoot.getRight();
            } else if (cmp > 0) {
                dataRoot = dataRoot.getLeft();
            } else {
                return dataRoot.getValue();
            }
        }
        return null;
    }

    public T remove(T value) {

        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> parent = root;
        System.out.println("移除之前");
        TreePrintUtil.pirnt(getRoot());
        while (dataRoot != null) {
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getRight();
            } else if (cmp > 0) {
                parent = dataRoot;
                dataRoot = dataRoot.getLeft();
            } else {//找到   中序遍历
                if (dataRoot.getRight() != null) {//待删除点的右节点不为空，不是叶子节点
                    RBTreeNode<T> min = removeMin(dataRoot.getRight());                    //x used for fix color balance
                    RBTreeNode<T> x = min.getRight() == null ? min.getParent() : min.getRight();//如果找到的点的右节点为空，返回父亲，否则返回右节点
                    boolean isParent = min.getRight() == null;
                    min.setLeft(dataRoot.getLeft());
                    setParent(dataRoot.getLeft(), min);
                    if (parent.getLeft() == dataRoot) {//判断待删除节点是父节点的右还是左节点
                        parent.setLeft(min);
                    } else {
                        parent.setRight(min);
                    }
                    setParent(min, parent);
                    boolean curMinIsBlack = min.isBlack();      //判断待移动节点的颜色
                    min.setRed(dataRoot.isRed());   //继承待删除节点的颜色
                    if (min != dataRoot.getRight()) {//如果待移动节点不是待删除节点的右节点
                        min.setRight(dataRoot.getRight());
                        setParent(dataRoot.getRight(), min);
                    }                    //remove a black node,need fix color
                    System.out.println("删除之后，修复之前");
                    TreePrintUtil.pirnt(getRoot());
                    if (curMinIsBlack) {
                        if (min != dataRoot.getRight()) {//如果待移动节点不是待删除节点的右节点
                            fixRemove(x, isParent);
                        } else if (min.getRight() != null) {
                            fixRemove(min.getRight(), false);
                        } else {
                            fixRemove(min, true);
                        }
                    }
                } else {
                    setParent(dataRoot.getLeft(), parent);
                    if (parent.getLeft() == dataRoot) {
                        parent.setLeft(dataRoot.getLeft());
                    } else {
                        parent.setRight(dataRoot.getLeft());
                    }//删除操作结束
                    System.out.println("删除之后，修复之前");
                    TreePrintUtil.pirnt(getRoot());
                    //current node is black and tree is not empty
                    if (dataRoot.isBlack() && !(root.getLeft() == null)) {
                        RBTreeNode<T> x = dataRoot.getLeft() == null
                                ? parent : dataRoot.getLeft();//左节点为空即表明待删除节点是叶子节点
                        boolean isParent = dataRoot.getLeft() == null;
                        fixRemove(x, isParent);
                    }
                }
                //删除该节点
                setParent(dataRoot, null);
                dataRoot.setLeft(null);
                dataRoot.setRight(null);
                if (getRoot() != null) {
                    getRoot().setRed(false);
                    getRoot().setParent(null);
                }
                size.decrementAndGet();
                return dataRoot.getValue();
            }
        }
        return null;
    }

    private void fixRemove(RBTreeNode<T> node, boolean isParent) {//node是待删除节点的右节点 或者父节点
        RBTreeNode<T> cur = isParent ? null : node;
        boolean isRed = isParent ? false : node.isRed();
        RBTreeNode<T> parent = isParent ? node : node.getParent();
        while (!isRed && !isRoot(cur)) {
            RBTreeNode<T> sibling = getSibling(cur, parent);            //sibling is not null,due to before remove tree color is balance
            //if cur is a left node
            boolean isLeft = parent.getRight() == sibling;
            if (sibling.isRed() && !isLeft) {//case 1
                //cur in right
                parent.makeRed();
                sibling.makeBlack();
                rotateRight(parent);
            } else if (sibling.isRed() && isLeft) {                //cur in left
                parent.makeRed();
                sibling.makeBlack();
                rotateLeft(parent);
            } else if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {//case 2
                sibling.makeRed();
                cur = parent;
                isRed = cur.isRed();
                parent = parent.getParent();
            } else if (isLeft && !isBlack(sibling.getLeft())
                    && isBlack(sibling.getRight())) {//case 3
                sibling.makeRed();
                sibling.getLeft().makeBlack();
                rotateRight(sibling);
            } else if (!isLeft && !isBlack(sibling.getRight()) && isBlack(sibling.getLeft())) {
                sibling.makeRed();
                sibling.getRight().makeBlack();
                rotateLeft(sibling);
            } else if (isLeft && !isBlack(sibling.getRight())) {//case 4
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getRight().makeBlack();
                rotateLeft(parent);
                cur = getRoot();
            } else if (!isLeft && !isBlack(sibling.getLeft())) {
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getLeft().makeBlack();
                rotateRight(parent);
                cur = getRoot();
            }
        }
        if (isRed) {
            cur.makeBlack();
        }
        if (getRoot() != null) {
            getRoot().setRed(false);
            getRoot().setParent(null);
        }

    }    //get sibling node

    private RBTreeNode<T> getSibling(RBTreeNode<T> node, RBTreeNode<T> parent) {
        parent = node == null ? parent : node.getParent();
        if (node == null) {
            return parent.getLeft() == null ? parent.getRight() : parent.getLeft();
        }
        if (node == parent.getLeft()) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

    private boolean isBlack(RBTreeNode<T> node) {
        return node == null || node.isBlack();
    }

    private boolean isRoot(RBTreeNode<T> node) {
        return root.getLeft() == node && node.getParent() == null;
    }

    private RBTreeNode<T> removeMin(RBTreeNode<T> node) {        //中序遍历的第一个节点
        RBTreeNode<T> parent = node;
        while (node != null && node.getLeft() != null) {
            parent = node;
            node = node.getLeft();
        }        //remove min node
        if (parent == node) {
            return node;
        }

        parent.setLeft(node.getRight());//把找到的节点的右节点移到他父亲的左节点上
        setParent(node.getRight(), parent);        //don't remove right pointer,it is used for fixed color balance
        //node.setRight(null);
        return node;
    }

    private RBTreeNode<T> findParentNode(RBTreeNode<T> x) {//寻找在哪个节点下插入
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> child = dataRoot;
        while (child != null) {
            int cmp = child.getValue().compareTo(x.getValue());
            if (cmp == 0) {
                return child;
            }
            if (cmp > 0) {
                dataRoot = child;
                child = child.getLeft();
            } else if (cmp < 0) {
                dataRoot = child;
                child = child.getRight();
            }
        }
        return dataRoot;
    }

    private void fixInsert(RBTreeNode<T> x) {
        RBTreeNode<T> parent = x.getParent();
        while (parent != null && parent.isRed()) {
            RBTreeNode<T> uncle = getUncle(x);
            if (uncle == null) {//need to rotate
                RBTreeNode<T> ancestor = parent.getParent();                //ancestor is not null due to before before add,tree color is balance
                if (parent == ancestor.getLeft()) {//父亲是左节点
                    boolean isRight = x == parent.getRight();//己是右节点
                    if (isRight) {
                        rotateLeft(parent);
                    }
                    rotateRight(ancestor);
                    if (isRight) {
                        x.setRed(false);
                        parent = null;//end loop  结束循环的一种手段
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                } else {
                    boolean isLeft = x == parent.getLeft();
                    if (isLeft) {
                        rotateRight(parent);
                    }
                    rotateLeft(ancestor);
                    if (isLeft) {
                        x.setRed(false);
                        parent = null;//end loop
                    } else {
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }
            } else {//uncle is red
                parent.setRed(false);
                uncle.setRed(false);
                parent.getParent().setRed(true);
                x = parent.getParent();
                parent = x.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    private RBTreeNode<T> getUncle(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> ancestor = parent.getParent();
        if (ancestor == null) {
            return null;
        }
        if (parent == ancestor.getLeft()) {
            return ancestor.getRight();
        } else {
            return ancestor.getLeft();
        }
    }

    private void rotateLeft(RBTreeNode<T> node) {//左旋，父亲变为左孩子
        RBTreeNode<T> right = node.getRight();
        if (right == null) {
            throw new java.lang.IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());//把待旋转的节点的左子节点转移到他父亲的右子节点上
        setParent(right.getLeft(), node);
        right.setLeft(node);
        setParent(node, right);
        if (parent == null) {//node pointer to root
            //right  raise to root node
            root.setLeft(right);
            setParent(right, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }            //right.setParent(parent);
            setParent(right, parent);
        }
    }

    private void rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> left = node.getLeft();
        if (left == null) {
            throw new java.lang.IllegalStateException("left node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());//原祖父的左节点设为原孙子的右节点
        setParent(left.getRight(), node);

        left.setRight(node);
        setParent(node, left);
        if (parent == null) {
            root.setLeft(left);
            setParent(left, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            setParent(left, parent);
        }
    }

    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
            if (parent == root) {
                node.setParent(null);
            }
        }
    }

    private void deleteNode(RBTreeNode<T> node) {
        //replace表示删除之后顶替上来的结点
        //parent为replace结点的父结点
        RBTreeNode<T> replace = null, parent = null;
        // 如果删除的结点左右孩子都有
        if (node.getLeft() != null && node.getRight() != null) {
            RBTreeNode<T> succ = null;
            for (succ = node.getRight(); succ.getLeft() != null; succ = succ.getLeft()) ;//找到后继
            node.setValue(succ.getValue());//覆盖值
            deleteNode(succ);//递归删除，只可能递归一次
            return;
        } else {// 叶子或只有一个孩子的情况
            // 如果删除的是根，则root指向其孩子(有一个红孩子或者为nil)
            if (node.getParent() == null) {
                // 如果有左孩子，那根就指向左孩子，没有则指向右孩子（可能有或者为NIL）
                root.setLeft((node.getLeft() != null ? node.getLeft() : node.getRight()));
                replace = this.root;
                if (getRoot() != null)
                    getRoot().setParent(null);
            } else {// 非根情况
                RBTreeNode<T> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
                if (node.getParent().getLeft() == node)
                    node.getParent().setLeft(child);
                else
                    node.getParent().setRight(child);

                if (child != null)
                    child.setParent(node.getParent());
                replace = child;
                parent = node.getParent();
            }
        }
        //如果待删除结点为红色，直接结束
        if (!node.isRed())
            deleteFixUp(replace, parent);
    }

    private void deleteFixUp(RBTreeNode<T> replace, RBTreeNode<T> parent) {
        RBTreeNode<T> brother = null;
        // 如果顶替结点是黑色结点，并且不是根结点。
        //由于经过了上面的deleteNode方法，这里面parent是一定不为null的
        while ((replace == null || !replace.isRed()) && replace != this.root) {
            //左孩子位置的所有情况，
            if (parent.getLeft() == replace) {
                brother = parent.getRight();
                // case1 红兄，brother涂黑，parent涂红，parent左旋，replace的兄弟改变了，变成了黑兄的情况
                if (brother.isRed()) {
                    brother.makeBlack();
                    parent.makeRed();
                    rotateLeft(parent);
                    brother = parent.getRight();
                }
                // 经过上面，不管进没进if，兄弟都成了黑色
                // case2 黑兄，且兄弟的两个孩子都为黑
                if ((brother.getLeft() == null || brother.getLeft().isBlack()) && (brother.getRight() == null || brother.getRight().isBlack())) {
                    // 如果parent此时为红，则把brother的黑色转移到parent上
                    if (parent.isRed()) {
                        parent.makeBlack();
                        brother.makeRed();
                        break;
                    } else {// 如果此时parent为黑，即此时全黑了，则把brother涂红，导致brother分支少一个黑，使整个分支都少了一个黑，需要对parent又进行一轮调整
                        brother.makeRed();
                        replace = parent;
                        parent = replace.getParent();
                    }
                } else {
                    // case3 黑兄，兄弟的左孩子为红色
                    if (brother.getLeft() != null && brother.getLeft().isRed()) {
                        if (parent.isRed())
                            brother.getLeft().makeRed();
                        else
                            brother.getLeft().makeBlack();
                        parent.makeBlack();
                        rotateRight(brother);
                        rotateLeft(parent);
                        // case4 黑兄，兄弟的右孩子为红色
                    } else if (brother.getRight() != null && brother.getRight().isRed()) {
                        if (parent.isRed())
                            brother.makeRed();
                        else
                            brother.makeBlack();
                        parent.makeBlack();
                        brother.getRight().makeBlack();
                        rotateLeft(parent);
                    }
                    break;
                }
            } else {//对称位置的情况，把旋转方向反回来
                brother = parent.getLeft();
                // case1 红兄，brother涂黑，parent涂红，parent左旋，replace的兄弟改变了，变成了黑兄的情况
                if (brother.isRed()) {
                    brother.makeBlack();
                    parent.makeRed();
                    rotateRight(parent);
                    brother = parent.getLeft();
                }
                // 经过上面，不管进没进if，兄弟都成了黑色
                // case2 黑兄，且兄弟的两个孩子都为黑
                if ((brother.getLeft() == null || brother.getLeft().isBlack())
                        && (brother.getRight() == null || brother.getRight().isBlack())) {
                    // 如果parent此时为红，则把brother的黑色转移到parent上
                    if (parent.isRed()) {
                        parent.makeBlack();
                        brother.makeRed();
                        break;
                    } else {// 如果此时parent为黑，即此时全黑了，则把brother涂红，导致brother分支少一个黑，使整个分支都少了一个黑，需要对parent又进行一轮调整
                        brother.makeRed();
                        replace = parent;
                        parent = replace.getParent();
                    }
                } else {
                    // case3 黑兄，兄弟的左孩子为红色，右孩子随意
                    if (brother.getRight() != null && brother.getRight().isRed()) {
                        if (parent.isRed())
                            brother.getRight().makeRed();
                        else
                            brother.getRight().makeBlack();
                        parent.makeBlack();
                        rotateLeft(brother);
                        rotateRight(parent);
                        // case4 黑兄，兄弟的右孩子为红色，左孩子随意
                    } else if (brother.getLeft() != null && brother.getLeft().isRed()) {
                        if (parent.isRed())
                            brother.makeRed();
                        else
                            brother.makeBlack();
                        parent.makeBlack();
                        brother.getLeft().makeBlack();
                        rotateRight(parent);
                    }
                    break;
                }
            }
        }
        //这里可以处理到删除结点为只有一个孩子结点的情况，如果是根，也会将其涂黑。
        if (replace != null)
            replace.makeBlack();
    }

    public static void main(String[] args) throws InterruptedException {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("c");
        bst.addNode("b");
        bst.addNode("f");
        bst.addNode("a");
        bst.addNode("e");
        bst.addNode("g");
        bst.addNode("h");
        // TreePrintUtil.pirnt(bst.getRoot());
        //bst.remove("c");
        // bst.remove("a");
       //  bst.deleteNode(new RBTreeNode<>("c"));
        //bst.printTree(bst.getRoot());
        System.out.println("最终结果");
        TreePrintUtil.pirnt(bst.getRoot());
    }
}
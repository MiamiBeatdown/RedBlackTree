public class testTree
{
	    public static void main(String[] args) {
	        map rbtree = new map(10,10);
	        rbtree.addNode(6, 6);
	        rbtree.addNode(11, 11);
	        rbtree.addNode(15, 15);
	        rbtree.addNode(2, 2);
	        rbtree.addNode(4, 4);
	        rbtree.addNode(1, 1);
	        rbtree.addNode(20, 20);
	        rbtree.addNode(14, 14);
	        rbtree.addNode(13, 13);
	        rbtree.addNode(12, 12);
	        rbtree.printTree();
	        System.out.println("Tree empty? - " + rbtree.checkEmpty());
	        map rbtreecopy = new map(null, null);
	        rbtreecopy.copyTree(rbtree);
	        rbtree.deleteTree();
	        System.out.println("New tree(copy):"); 
	        rbtreecopy.printTree();
	        System.out.println("After delete tree empty? - " + rbtree.checkEmpty());
	        System.out.println("value 11 in tree?:" + rbtreecopy.searchKey(11));
	        System.out.println("value 17 in tree?:" + rbtreecopy.searchKey(17));
	    }
}
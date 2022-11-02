class map
{
	int n = 0;
	node root;
	public map(Comparable key, Comparable value)
	{
		n = 1;
		pair p = new pair(key, value);
		this.root = new node(p);
		root.color = false;
	}
	public boolean checkEmpty()
	{
		if(root == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void deleteTree()
	{
		if(root == null)
		{
			return;
		}
		deleteNode(root.left);
		deleteNode(root.right);
		root = null;
	}
	public void deleteNode(node a)
	{
		if(a == null)
		{
			return;
		}
		deleteNode(a.left);
		deleteNode(a.right);
		a = null;
	}
	public boolean searchKey(Comparable key)
	{
		if(root == null)
		{
			return false;
		}
		node tmp = root;
		while(tmp != null)
		{
			if(tmp.data.getKey().compareTo(key) != 0)
			{
				if(key.compareTo(tmp.data.getKey()) < 0)
				{
					tmp = tmp.left;
				}
				else
				{
					tmp = tmp.right;
				}
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	public Comparable searchAndGet(Comparable key)
	{
		if(root == null)
		{
			return null;
		}
		node tmp = root;
		if(tmp.data.getKey().compareTo(key) != 0)
		{
			if(key.compareTo(tmp.data.getKey()) < 0)
			{
				tmp = tmp.left;
			}
			else
			{
				tmp = tmp.right;
			}
		}
		else
		{
			return tmp.data.getValue();
		}
		return null;
	}
	public node searchNode(Comparable key)
	{
		node tmp= this.root;
		while(tmp != null)
		{
			if(key.compareTo(tmp.data.getKey()) == 0)
			{
				return tmp;
			}
			else if(key.compareTo(tmp.data.getKey()) == -1)
			{
				tmp = tmp.left;
			}
			else 
			{
				tmp = tmp.right;
			}
		}
		return null;
	}
	public void copyTree(map tree)
	{
		root = new node(tree.root.data);
		root.parent = null;
		root.color = false;
		copyNode(this.root, tree.root);
	}
	public void copyNode(node x, node y)
	{
		if(y == null)
		{
			return;
		}
		if(y.left != null)
		{
			x.left = new node(y.left.data);
			x.left.color = y.left.color; 
			x.left.parent = x;	
			copyNode(x.left, y.left);
		}
		if(y.right != null)
		{
			x.right = new node(y.right.data);
			x.right.color = y.right.color; 
			x.right.parent = x;
			copyNode(x.right, y.right);
		}
	}
	public void addNode(Comparable key, Comparable value)
	{
		n++;
		pair p = new pair(key, value);
        node newNode = new node(p);
        if(root == null)
        {
        	root = newNode;
        	newNode.color = false;
        	root.parent = null;
        	return; 
        }
        node tmp = root;
        while(tmp != null)
        {
        	if(value.compareTo(tmp.data.getValue()) < 0)
        	{
        		if(tmp.left == null)
        		{
        			newNode.parent = tmp;
        			tmp.left = newNode;
        			break;
        		}
        		else
        		{
        			tmp = tmp.left;
        		}
        	}
        	else
        	{
        		if(tmp.right == null)
        		{
        			newNode.parent = tmp;
        			tmp.right = newNode;
        			break;
        		}
        		else
        		{
        			tmp = tmp.right;
        		}
        	}
        }
        addFix(newNode);
	}
	void leftRotate(node a)
	{
		node b = a.right;
		a.right = b.left;
		if(a.right != null) 		
		{
			a.right.parent = a; 	
		}
		b.parent = a.parent; 
		if(a.parent == null)		
		{
			this.root = b;			
		}
		else if(a.parent.left == a)	
		{
			a.parent.left = b;		
		}
		else						
		{
			a.parent.right = b;
		}
		b.left = a;
		a.parent = b; 
	}
	void rightRotate(node a)		
	{
		node b = a.left;
		a.left = b.right;  
		if(a.left != null) 
		{
			a.left.parent = a;
		}
		b.parent = a.parent;
		if(a.parent == null)
		{
			this.root = b;
		}
		else if(a.parent.right == a)
		{
			a.parent.right = b;
		}
		else
		{
			a.parent.left = b;
		}
		b.right = a;
		a.parent = b;
	}
	void addFix(node x)
	{
		if(x == this.root)							
		{
			x.color = false;
			return;
		}
		if(x.parent.color  == false)			
		{
			return;
		}
		else										
		{
			if(x.parent == x.parent.parent.left)	
			{
				node u = x.parent.parent.right;
				if(u != null && u.color == true)	
				{
					x.parent.color = false;
					u.color = false;
					x.parent.parent.color = true;
					x = x.parent.parent;			
					addFix(x);
				}
				else								
				{
					if(x == x.parent.right)
					{
						x = x.parent;
						leftRotate(x);
					}
					x.parent.color = false;
					x.parent.parent.color = true;
					rightRotate(x.parent.parent);
				}	
			}
			else
			{
				node u = x.parent.parent.left;
				if(u != null && u.color == true)	
				{
					x.parent.color = false;
					u.color = false;
					x.parent.parent.color = true;
					x = x.parent.parent; 			
					addFix(x);
				}
				else								
				{
					if(x == x.parent.left)
					{
						x = x.parent;
						rightRotate(x);
					}
					x.parent.color = false;
					x.parent.parent.color = true;
					leftRotate(x.parent.parent);
				}
			}
		}
		this.root.color = false;
	}
	public void printTree()
	{
		printNode(this.root, "");
	}
	public void printNode(node a, String str)
	{
		if(a.left != null)
		{
			printNode(a.left, str + "/");
		}
		System.out.println(str + a.data + " ");
		if(a.color)
		{
			System.out.println("red");
		}
		else
		{
			System.out.println("black");
		}
		if(a.right != null)
		{
			printNode(a.right, str + "\\");
		}
	}
	public int comparator(pair data)
	{
		node a = new node(null);
		node b = new node(data);
		b.data.value = data.getValue();
		a = searchNode(data.getKey());
		if(a == null)
		{
			return -2;
		}
		if(a.data.getValue().compareTo(b.data.getValue()) > 0)
		{
			return 1;
		}		
		else if(a.data.getValue().compareTo(b.data.getValue()) < 0)
		{
			return -11;
		}		
		else if(a.data.getValue().compareTo(b.data.getValue()) == 0)
		{
			return 0;
		}
		return -3;
	}
}
class node
{
	public pair data;;
	public node  left;
	public node  right;
	public node  parent;
	boolean color;
	// true  - red, 
	//false - black;
	public node(pair a)
	{
		this.data = a;
		this.color = true;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}
class pair
{
	public Comparable key;
	public Comparable value;
	public pair(Comparable key, Comparable value)
	{
		this.key = key;
		this.value = value;
	}
	public Comparable getKey()
	{
		return key;
	}
	public Comparable getValue()
	{
		return value;
	}
	public void giveValue(Comparable data)
	{
		this.value = data;
	}
	public int compareTo(pair a)
	{
		return this.value.compareTo(a.value);
	}
	public String toString()
	{
		return getKey() + ", " + getValue();
	}
}
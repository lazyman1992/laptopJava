package Tree;

import java.util.HashMap;

public class BinaryTree {
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data){
			value=data;
		}
	}
	
	public static int getMaxlen(int[] arr,int k){
		if(arr==null||arr.length<0)
			return 0;
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		map.put(0, -1);
		int sum=0;
		int len=0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
			if(map.containsKey(sum-k))
				len=Math.max(i-map.get(sum-k),len);
			if(!map.containsKey(sum))
				map.put(sum, i);
		}
		return len;
	}
	public static void traveInOrder(Node head){
		if(head==null)
			return ;
		if(head.left!=null)
		traveInOrder(head.left);
		System.out.print(head.value+" ");
		if(head.right!=null)
			traveInOrder(head.right);
	}
	
	
	public static void morrisIn(Node head){
		if(head==null)
			return;
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null){
			cur2=cur1.left;
			if(cur2!=null){
				while(cur2.right!=null&&cur2.right!=cur1)
					cur2=cur2.right;
				if(cur2.right==null){
					cur2.right=cur1;
					cur1=cur1.left;
					continue;
				}
				else{
					cur2.right=null;
				}
				
			}
			System.out.print(cur1.value+" ");
			cur1=cur1.right;
		}
		System.out.println();
	}
	
	
	
	public static int preOrder(Node head,int sum,int preSum,int level,int maxLen,HashMap<Integer,Integer> sumMap){
		if(head==null)
			return 0;
		int curSum=head.value+preSum;
		if(!sumMap.containsKey(curSum))
			sumMap.put(curSum, level);
		if(sumMap.containsKey(curSum-sum))
			maxLen=Math.max(level-sumMap.get(curSum-sum), maxLen);
		maxLen=preOrder(head.left,sum,curSum,level+1,maxLen,sumMap);
		maxLen=preOrder(head.right,sum,curSum,level+1,maxLen,sumMap);
		if(level==sumMap.get(curSum))
			sumMap.remove(curSum);
		return maxLen;
		
	}
	
	public static int getMaxLen(Node head,int sum){
		if(head==null)
			return 0;
		HashMap<Integer,Integer> sumMap=new HashMap<Integer,Integer>();
		sumMap.put(0, 0);
		return preOrder(head,sum,0,1,0,sumMap);
	}
	
	
	public static Node posOrder(Node head,int[] record){
		if(head==null){
			record[0]=0;
			record[1]=Integer.MIN_VALUE;
			record[2]=Integer.MAX_VALUE;
			return null;
		}
		int value=head.value;
		Node left=head.left;
		Node right=head.right;
		Node lBST=posOrder(left,record);
		int lSize=record[0];
		int lMin=record[1];
		int lMax=record[2];
		Node rBST=posOrder(right,record);
		int rSize=record[0];
		int rMin=record[1];
		int rMax=record[2];
		record[1]=Math.max(lMin, value);
		record[2]=Math.min(rMax, value);
		if(left==lBST&&right==rBST&&lMax<value&&value<rMin){
			record[0]=lSize+rSize+1;
			return head;
		}
		record[0]=Math.max(lSize, rSize);
		return lSize>rSize?lBST:rBST;
	}
	
	//chazhao zuida sousuoerchashu
	public static Node biggestSubBST(Node head){
		int[] record =new int[3];
		return posOrder(head,record);
	}
	
	
	
	public static void main(String[] args){
		Node head=new Node(1);
		head.left=new Node(2);
		head.right=new Node(3);
		head.left.right=new Node(4);
		head.left.right.left=new Node(7);
		head.left.right.right=new Node(8);
		head.left.right.right.right=new Node(11);
		head.left.right.right.right.left=new Node(13);
		head.left.right.right.right.right=new Node(14);
		head.right.left=new Node(5);
		head.right.right=new Node(6);
		head.right.left.left=new Node(9);
		head.right.left.right=new Node(10);
		head.right.left.left.left=new Node(12);
		head.right.left.left.left.left=new Node(15);
		head.right.left.left.left.right=new Node(16);
		
		//int[] arr={1,2,3,3};
		//int k=6;
		
		//traveInOrder(head);
		//System.out.println(getMaxlen(arr,k));
		//morrisIn(head);
		
		System.out.println(getMaxLen(head,7));
				
	}
	

}

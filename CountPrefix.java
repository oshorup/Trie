public class CountPrefix {
    public static void main(String[] args) {
        TrieNodeModified tnm = new TrieNodeModified();
        tnm.root=new TrieNodeModified();

        tnm.insert("aaaaa");
        tnm.insert("abcd");
        tnm.insert("aabcd");
        tnm.insert("aabsb");

        System.out.println(tnm.countPrefix("ab"));
    }
    
}
class TrieNodeModified{
    private static final int ALPHABET_SIZE = 26;
    int pc;//prefic count
    TrieNodeModified [] child;
    TrieNodeModified(){
        this.pc = 0;
        this.child=new TrieNodeModified[ALPHABET_SIZE];
        for(int i=0;i<ALPHABET_SIZE;i++){
            child[i]=null;
        }
    }
    TrieNodeModified root;

    public void insert(String word){
        int level;
        int len=word.length();
        TrieNodeModified rModified = root;
        for(level=0;level<len;level++){
            int index = word.charAt(level)-'a';
            
            //check whether user has entered correct lowercase word string containing char from 'a' to 'z' or not
            if(index>=rModified.child.length || index<0)return;

            if(rModified.child[index]==null){
                rModified.child[index]=new TrieNodeModified();
            }
            rModified.child[index].pc+=1; //increment prefix count each time new character is added

            //now update
            rModified=rModified.child[index];
        }
    }
    public int countPrefix(String prefix){
        int level;
        int len=prefix.length();
        TrieNodeModified rModified = root;
        for(level=0;level<len;level++){
            int index = prefix.charAt(level)-'a';
            
            //check whether user has entered correct lowercase prefix string containing char from 'a' to 'z' or not
            if(index>=rModified.child.length || index<0)return 0;

            //when there is no prefix
            if(rModified.child[index]==null || rModified.child[index].pc==0)return 0;

            //now update
            rModified=rModified.child[index];
        }
        //in last return whatever value of last node's pc have
        return rModified!=null?rModified.pc:0;
    }
}

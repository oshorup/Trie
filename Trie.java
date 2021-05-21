import java.util.Scanner;

public class Trie {
    public static void main(String[] args) {
        TrieNode tNode = new TrieNode();
        tNode.root = new TrieNode();

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print(
                    "You have 3 choices:\n For inserting, press 1\n For searching, press 2\n for deleting, a word press 3\n For deleting all occurences a particular word, press 4\n");
            int order = s.nextInt();
            if (order == 1) {
                // insert
                System.out.println("Enter the word(lowercase) to add in dictionary");
                String word = s.next();
                boolean isAdded = tNode.insert(word);
                System.out.println(isAdded?"word added in the dictionary":"Unable to add word, please check your word again");
            } else if (order == 2) {
                // search
                System.out.println("Enter the word(lowercase) to search in dictionary");
                String word = s.next();
                boolean isAv = tNode.search(word);
                System.out.println(isAv ? "word available in dictionary" : "word not available in dictionary");
            } else if (order == 3) {
                // delete
                System.out.println("Enter the word(lowercase) to delete from the dictionary");
                String word = s.next();
                boolean isDeleted = tNode.deleteWord(word);
                System.out.println(isDeleted?"one occurance of " + word + " is deleted from the dictionary":"Unable to delete that word");
            } else if(order==4) {
                //delete all occurances
                System.out.println("Enter the word(lowercase) to delete all of its occurances from the dictionary");
                String word = s.next();
                boolean isDeleted = tNode.deleteAllWord(word);
                System.out.println(isDeleted?"All occurances of " + word + " are deleted from the dictionary":"Unable to delete");

            }else{
                System.out.println("You entered wrong input. Terminating program...");
                break;
            }
        }
        s.close();
    }
}

class TrieNode {
    char c;
    TrieNode[] child;
    int word_count;

    final int ALPHABET_SIZE = 26;
    TrieNode root;

    public TrieNode(char ch) {
        this.c = ch;
        this.word_count = 0;
        this.child = new TrieNode[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++)
            child[i] = null;
    }

    public TrieNode() {
        this.word_count = 0;
        this.child = new TrieNode[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++)
            child[i] = null;
    }

    public boolean insert(String word) {
        TrieNode rNode = root;
        int len = word.length();
        int level;
        for (level = 0; level < len; level++) {
            int index = word.charAt(level) - 'a';

            //when user tries to delete wrong type of word that is not a lowercase word and not containing characters only from 'a' to 'z'
            if(index>=rNode.child.length||index<0)return false;

            if (rNode.child[index] == null) {
                // if this char is not a child from before, then create
                rNode.child[index] = new TrieNode(word.charAt(level));
            }
            // move this rNode to its child
            rNode = rNode.child[index];
        }
        // In last, make the last char as the word ends here by incrementing its word
        // count
        rNode.word_count += 1;
        return true;
    }

    public boolean search(String word) {
        TrieNode rNode = root;
        int len = word.length();
        int level;
        for (level = 0; level < len; level++) {
            int index = word.charAt(level) - 'a';

            //check for wrong input word
            if(index>=rNode.child.length || index<0)return false;

            if (rNode.child[index] == null)
                return false;

            // update
            rNode = rNode.child[index];
        }
        if (rNode != null && rNode.word_count != 0)
            return true;
        else
            return false;
    }

    //delete single occurance of a particula word
    public boolean deleteWord(String word) {
        TrieNode rNode = root;
        int level;
        int len = word.length();
        for (level = 0; level < len; level++) {
            int index = word.charAt(level) - 'a';

            //check for wrong input word
            if(index>=rNode.child.length||index<0)return false;

            // if word is not available from before
            if (rNode.child[index] == null)
                return false;

            rNode = rNode.child[index];
        }

        if (rNode != null && rNode.word_count > 0) {
            rNode.word_count -= 1; // decrementing by one means we are not removing all words which are equal to the word to be deleted, deleting only one word of them
             return true;
        }
        return false;
    }
    //delete all occurance of a certain word
    public boolean deleteAllWord(String word){
        TrieNode rNode = root;
        int level;
        int len = word.length();
        for (level = 0; level < len; level++) {
            int index = word.charAt(level) - 'a';
            
            //check for wrong input word
            if(index>=rNode.child.length||index<0)return false;

            // if word is not available from before
            if (rNode.child[index] == null)
                return false;

            rNode = rNode.child[index];
        }

        if (rNode != null && rNode.word_count > 0) {
            rNode.word_count = 0; //setting to zero, means there is no word that ends here at this node, so automatically deleted all
            return true;
        }
        return false;
    }
}

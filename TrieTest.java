import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by ShlokDixit on 09/12/16.
 */


    class TrieNode
    {
        char content;
        boolean isEnd;
        int count;
        LinkedList<TrieNode> childList;

        /* Constructor */
        public TrieNode(char c)
        {
            childList = new LinkedList<TrieNode>();
            isEnd = false;
            content = c;
            count = 0;
        }
        public TrieNode subNode(char c)
        {
            if (childList != null)
                for (TrieNode eachChild : childList)
                    if (eachChild.content == c)
                        return eachChild;
            return null;
        }
    }

    class Trie
    {
        private TrieNode root;

        /* Constructor */
        public Trie()
        {
            root = new TrieNode(' ');
        }
        /* Function to insert word */

        public void insert(String word)
        {
            if (search(word) == true)
                return;
            TrieNode current = root;
            for (char ch : word.toCharArray() )
            {
                TrieNode child = current.subNode(ch);
                if (child != null)
                    current = child;
                else
                {
                    current.childList.add(new TrieNode(ch));
                    current = current.subNode(ch);
                }
                current.count++;
            }
            current.isEnd = true;
        }
        /* Function to search for word */
        public boolean search(String word)
        {
            TrieNode current = root;
            for (char ch : word.toCharArray() )
            {
                if (current.subNode(ch) == null)
                    return false;
                else
                    current = current.subNode(ch);
            }
            if (current.isEnd == true)
                return true;
            return false;
        }


    }

    /* Class Trie Test */
    public class TrieTest extends searchEngine
    {
        public static void main(String[] args)
        {


            Scanner scan = new Scanner(System.in);
        /* Creating object of AATree */
            Trie t = new Trie();
            TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>( );

            readWordFile(frequencyData);
            printAllCounts(frequencyData);
            for ( String key : frequencyData.keySet() ) {
                //System.out.println( key );
                t.insert(key);

            }



            System.out.println("Enter the words to be searched\n");
            char ch;
        /*  Perform tree operations  */
            do
            {
                System.out.println("Enter string element to search");
                System.out.println("Search result : "+ t.search( scan.next() ));
                System.out.println("\nDo you want to continue (Type y or n) \n");
                ch = scan.next().charAt(0);
            } while (ch == 'Y'|| ch == 'y');
        }
    }


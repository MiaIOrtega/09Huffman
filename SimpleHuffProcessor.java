/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <Chinedum Nwogu> (and <NAME2),
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1: Chinedum Nwogu idiot idiot
 *  UTEID: cgn477
 *  email address: cgn477@my.utexas.edu
 *
 *  Student 2: Mia Ortega
 *  UTEID: 
 *  email address:
 *
 *  Grader name:
 *  Section number:
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private boolean methodCalled;
    private HashMap<Integer, Integer> charFrequencies;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        this.methodCalled = true;
        this.charFrequencies = new HashMap<>();
        BitInputStream bis = new BitInputStream(in);
        readInFile(bis);
        
        HuffmanTree huffmanCodingTree = new HuffmanTree();
        FairPriorityQueue<TreeNode> queue = new FairPriorityQueue<>();
        for (int key : charFrequencies.keySet()) {
            TreeNode node = new TreeNode(key, charFrequencies.get(key));
            queue.enqueue(node);
        }
        
        while (queue.size() != 1) {     // I think this implementation is good
            TreeNode n1 = queue.dequeue();
            TreeNode n2 = queue.dequeue();
            
            huffmanCodingTree.add(n1, n2);
            queue.enqueue(huffmanCodingTree.getRootNode());
        }
        
        showString("Not working yet");
        myViewer.update("Still not working");
        throw new IOException("preprocess not implemented");
        //return 0;
    }
    
    private void readInFile(BitInputStream bis) throws IOException {    // idk if I'm doing this correct :(
        boolean done = false;
        while (!done) {
            int bit = bis.readBits(BITS_PER_WORD);
            if (bit == -1) {
                throw new IOException("Error reading file");
            } else {
                if (charFrequencies.containsKey(bit)) {
                    charFrequencies.put(bit, charFrequencies.get(bit) + 1);
                } else {
                    charFrequencies.put(bit, 1);
                }
            }
            
            if (charFrequencies.size() == ALPH_SIZE) {
                done = true;
            }
        }
        
        charFrequencies.put(PSEUDO_EOF, 1);
    }

    /**
	 * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        // checks preconditions
        if (!methodCalled) {
            throw new IllegalStateException("Violation of precondition: compress."
                    + " preprocessCompress must be called before compress.");
        }
        throw new IOException("compress is not implemented");
        
        //return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        BitInputStream bis = new BitInputStream(in);
        BitOutputStream bos = new BitOutputStream(out);

	        throw new IOException("uncompress not implemented");
	        //return 0;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s){
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}

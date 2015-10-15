package exampleCode;

/* 
 * Read and write fasta files
 * Bioinformatics 1, WS 15/16
 * Dr. Kay Nieselt and Alexander Seitz
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/** 
 * Read and write FastA files
 */
public class FastA extends FastABase {
	/**
	 * read header and sequence in fasta format
	 * @param fastaFileReader
	 * @throws java.io.IOException
	 */
	public void read (BufferedReader fastaFileReader) throws IOException {
		String line = null;
		String header = null;
		String sequence = null;

		while ((line = fastaFileReader.readLine()) != null) {
			if (line.startsWith(">")) {
				if (header != null && sequence != null) {
					// add the stored header and sequence
					add(header, sequence);
					// reset variables
					header = null;
					sequence = null;
				}
				header = line.trim();
			} else {
				if (sequence == null)
					sequence = line.trim();
				else
					sequence += line.trim();
			}
		}

		// add last header sequence pair
		if (header != null && sequence != null)
			add(header, sequence);

		fastaFileReader.close();
	}

	/**
	 * write header and sequence in fasta format
	 * @param fastaFileWriter
	 * @throws IOException
	 */
	public void write (BufferedWriter fastaFileWriter) throws IOException {
		/* 
		 * print 80 characters per line 
		 */
		for (int i = 0; i < getSize(); i++) {
			String header = getHeader(i);
			String sequence = getSequence(i);

			fastaFileWriter.write(header);
			fastaFileWriter.newLine();
			fastaFileWriter.write(wrapString(sequence, 80));
			fastaFileWriter.newLine();
		}
		
		fastaFileWriter.close();
	}

	private String wrapString(String sequence, int maxLength) {
		int length = sequence.length();
		int wraps = length / maxLength;
		String wrapped = "";
		for (int i = 0; i < wraps; i++) {
			wrapped += sequence.substring(i * maxLength, (i+1) * maxLength) + "\n";
		}
		// rest of string
		wrapped += sequence.substring(wraps * maxLength, length);
		return wrapped.trim();
	}

	/**
	 * reads sequences from a file and write them to standard output
	 * @param args 
	 * @throws Exception 
	 */
	public static void main (String args[]) throws Exception {
		if(args.length!=1)
			throw new Exception("Usage: fasta file");

		FastA fasta = new FastA();

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		fasta.read(reader);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		fasta.write(writer);
	}
}

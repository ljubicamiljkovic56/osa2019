package projekat.osa.search.analyzers;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import projekat.osa.search.filters.CyrilicToLatinFilter;


public class SerbianAnalyzer extends Analyzer {

	
    public static final String[] STOP_WORDS =
    {
        "i","a","ili","ali","pa","te","da","u","po","na"
    };

    
    public SerbianAnalyzer(){}



    @Override
	protected TokenStreamComponents createComponents(String arg0) {
		Tokenizer source = new StandardTokenizer();
		TokenStream result = new CyrilicToLatinFilter(source);
	    result = new LowerCaseFilter(result);
	    result = new StopFilter(result,StopFilter.makeStopSet(STOP_WORDS));
		return new TokenStreamComponents(source, result){
		      protected void setReader(final Reader reader) {
		        setReader(reader);
		      }
		    };
	}
	

}

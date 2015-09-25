package spell.english;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import spell.services.DictionaryService;

/**
 * An implementation of the Dictionary service containing English words
 * see DictionaryService for details of the service.
 **/
@Component // It's an iPOJO Component
@Provides // We provide a service
@Instantiate // We declare an instance of our component
public class EnglishDictionary implements DictionaryService {

    // The set of words contained in the dictionary.
    String[] dictionary = { "welcome", "to", "the", "ipojo", "tutorial" };

    /**
     * Implements DictionaryService.checkWord(). Determines
     * if the passed in word is contained in the dictionary.
     * @param word the word to be checked.
     * @return true if the word is in the dictionary,
     *         false otherwise.
     **/
    public boolean checkWord(String word) {
        word = word.toLowerCase();

        // This is very inefficient
        for (String dict : dictionary) {
            if (dict.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
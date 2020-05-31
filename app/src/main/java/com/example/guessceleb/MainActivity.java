package com.example.guessceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> imgurl=new ArrayList<String>();
    ArrayList<String> nameurl=new ArrayList<String>();
    String name;
    int ChosenCeleb=0;
    ImageView imageView;
    String[] answers=new String[4];
    int locationOfCorrectAns=0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;


    public void strings(){
        name="<div class=\"lister-list\">\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000136/?ref_=nmls_pst\"> <img alt=\"Johnny Depp\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTM0ODU5Nzk2OV5BMl5BanBnXkFtZTcwMzI2ODgyNQ@@._V1_UY209_CR3,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">1. </span>\n" +
                "<a href=\"/name/nm0000136?ref_=nmls_hd\"> Johnny Depp\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0408236/?ref_=nmls_kf\"> Sweeney Todd: The Demon Barber of Fleet Street\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Johnny Depp is perhaps one of the most versatile actors of his day and age in Hollywood.<br><br>He was born John Christopher Depp II in Owensboro, Kentucky, on June 9, 1963, to Betty Sue (Wells), who worked as a waitress, and John Christopher Depp, a civil engineer.<br><br>Depp was raised in Florida. He dropped ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000199/?ref_=nmls_pst\"> <img alt=\"Al Pacino\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQzMzg1ODAyNl5BMl5BanBnXkFtZTYwMjAxODQ1._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">2. </span>\n" +
                "<a href=\"/name/nm0000199?ref_=nmls_hd\"> Al Pacino\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0070666/?ref_=nmls_kf\"> Serpico\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Alfredo James \"Al\" 'Pacino established himself as a film actor during one of cinema's most vibrant decades, the 1970s, and has become an enduring and iconic figure in the world of American movies.<br><br>He was born April 25, 1940 in Manhattan, New York City, to Italian-American parents, Rose (nee Gerardi)...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000134/?ref_=nmls_pst\"> <img alt=\"Robert De Niro\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjAwNDU3MzcyOV5BMl5BanBnXkFtZTcwMjc0MTIxMw@@._V1_UY209_CR9,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">3. </span>\n" +
                "<a href=\"/name/nm0000134?ref_=nmls_hd\"> Robert De Niro\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0081398/?ref_=nmls_kf\"> Raging Bull\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City, to artists Virginia (Admiral) and <a href=\"/name/nm3902582?ref_=nmls_mkdn\">Robert De Niro Sr.</a> His paternal grandfather was of Italian descent, and his other ancestry is Irish, English, Dutch, German, and French. He was trained ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000228/?ref_=nmls_pst\"> <img alt=\"Kevin Spacey\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTY1NzMyODc3Nl5BMl5BanBnXkFtZTgwNzE2MzA1NDM@._V1_UY209_CR58,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">4. </span>\n" +
                "<a href=\"/name/nm0000228?ref_=nmls_hd\"> Kevin Spacey\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0114814/?ref_=nmls_kf\"> The Usual Suspects\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Kevin Spacey Fowler, better known by his stage name Kevin Spacey, is an American actor of screen and stage, film director, producer, screenwriter and singer. He began his career as a stage actor during the 1980s before obtaining supporting roles in film and television. He gained critical acclaim in...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "<div class=\"list-description\"><p>He always is My favorite</p></div>    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000243/?ref_=nmls_pst\"> <img alt=\"Denzel Washington\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjE5NDU2Mzc3MV5BMl5BanBnXkFtZTcwNjAwNTE5OQ@@._V1_UY209_CR8,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">5. </span>\n" +
                "<a href=\"/name/nm0000243?ref_=nmls_hd\"> Denzel Washington\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt2671706/?ref_=nmls_kf\"> Fences\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Denzel Hayes Washington, Jr. was born on December 28, 1954 in Mount Vernon, New York. He is the middle of three children of a beautician mother, Lennis, from Georgia, and a Pentecostal minister father, Denzel Washington, Sr., from Virginia. After graduating from high school, Denzel enrolled at ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000128/?ref_=nmls_pst\"> <img alt=\"Russell Crowe\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQyMTExNTMxOF5BMl5BanBnXkFtZTcwNDg1NzkzNw@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">6. </span>\n" +
                "<a href=\"/name/nm0000128?ref_=nmls_hd\"> Russell Crowe\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1707386/?ref_=nmls_kf\"> Les Misérables\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Russell Ira Crowe was born in Wellington, New Zealand, to Jocelyn Yvonne (Wemyss) and John Alexander Crowe, both of whom catered movie sets. His maternal grandfather, Stanley Wemyss, was a cinematographer. Crowe's recent ancestry includes Welsh (where his paternal grandfather was born, in Wrexham),...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000093/?ref_=nmls_pst\"> <img alt=\"Brad Pitt\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjA1MjE2MTQ2MV5BMl5BanBnXkFtZTcwMjE5MDY0Nw@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">7. </span>\n" +
                "<a href=\"/name/nm0000093?ref_=nmls_hd\"> Brad Pitt\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Producer <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt2935510/?ref_=nmls_kf\"> Ad Astra\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    An actor and producer known as much for his versatility as he is for his handsome face, Golden Globe-winner Brad Pitt's most widely recognized role may be Tyler Durden in <a href=\"/title/tt0137523?ref_=nmls_mkdn\">Fight Club</a> (1999). However, his portrayals of Billy Beane in <a href=\"/title/tt1210166?ref_=nmls_mkdn\">Moneyball</a> (2011), and Rusty Ryan in the remake of <a href=\"/title/tt0240772?ref_=nmls_mkdn\">Ocean's Eleven</a> (...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0001401/?ref_=nmls_pst\"> <img alt=\"Angelina Jolie\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BODg3MzYwMjE4N15BMl5BanBnXkFtZTcwMjU5NzAzNw@@._V1_UY209_CR15,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">8. </span>\n" +
                "<a href=\"/name/nm0001401?ref_=nmls_hd\"> Angelina Jolie\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1587310/?ref_=nmls_kf\"> Maleficent\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Angelina Jolie is an Academy Award-winning actress who rose to fame after her role in <a href=\"/title/tt0172493?ref_=nmls_mkdn\">Girl, Interrupted</a> (1999), playing the title role in the \"Lara Croft\" blockbuster movies, as well as <a href=\"/title/tt0356910?ref_=nmls_mkdn\">Mr. &amp; Mrs. Smith</a> (2005), <a href=\"/title/tt0493464?ref_=nmls_mkdn\">Wanted</a> (2008), <a href=\"/title/tt0944835?ref_=nmls_mkdn\">Salt</a> (2010) and <a href=\"/title/tt1587310?ref_=nmls_mkdn\">Maleficent</a> (2014). Off-screen, Jolie has become ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000138/?ref_=nmls_pst\"> <img alt=\"Leonardo DiCaprio\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_UY209_CR7,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">9. </span>\n" +
                "<a href=\"/name/nm0000138?ref_=nmls_hd\"> Leonardo DiCaprio\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1375666/?ref_=nmls_kf\"> Inception\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Few actors in the world have had a career quite as diverse as Leonardo DiCaprio's. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom <a href=\"/title/tt0088527?ref_=nmls_mkdn\">Growing Pains</a> (1985) and low budget horror movies, such as <a href=\"/title/tt0101627?ref_=nmls_mkdn\">Critters 3</a> (1991), to a major teenage heartthrob in the 1990s,...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000129/?ref_=nmls_pst\"> <img alt=\"Tom Cruise\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTk1MjM3NTU5M15BMl5BanBnXkFtZTcwMTMyMjAyMg@@._V1_UY209_CR9,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">10. </span>\n" +
                "<a href=\"/name/nm0000129?ref_=nmls_hd\"> Tom Cruise\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0092099/?ref_=nmls_kf\"> Top Gun\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    In 1976, if you had told fourteen-year-old Franciscan seminary student Thomas Cruise Mapother IV that one day in the not too distant future he would be Tom Cruise, one of the top 100 movie stars of all time, he would have probably grinned and told you that his ambition was to join the priesthood. ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000237/?ref_=nmls_pst\"> <img alt=\"John Travolta\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTUwNjQ0ODkxN15BMl5BanBnXkFtZTcwMDc5NjQwNw@@._V1_UY209_CR7,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">11. </span>\n" +
                "<a href=\"/name/nm0000237?ref_=nmls_hd\"> John Travolta\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0110912/?ref_=nmls_kf\"> Pulp Fiction\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    John Joseph Travolta was born in Englewood, New Jersey, one of six children of <a href=\"/name/nm0871455?ref_=nmls_mkdn\">Helen Travolta</a> (née Helen Cecilia Burke) and Salvatore/Samuel J. Travolta. His father was of Italian descent and his mother was of Irish ancestry. His father owned a tire repair shop called Travolta Tires in Hillsdale, ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000216/?ref_=nmls_pst\"> <img alt=\"Arnold Schwarzenegger\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTI3MDc4NzUyMV5BMl5BanBnXkFtZTcwMTQyMTc5MQ@@._V1_UY209_CR13,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">12. </span>\n" +
                "<a href=\"/name/nm0000216?ref_=nmls_hd\"> Arnold Schwarzenegger\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0093773/?ref_=nmls_kf\"> Predator\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood's biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000230/?ref_=nmls_pst\"> <img alt=\"Sylvester Stallone\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQwMTk3NDU2OV5BMl5BanBnXkFtZTcwNTA3MTI0Mw@@._V1_UY209_CR5,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">13. </span>\n" +
                "<a href=\"/name/nm0000230?ref_=nmls_hd\"> Sylvester Stallone\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0075148/?ref_=nmls_kf\"> Rocky\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    This athletically built, dark-haired American actor/screenwriter/director may never be mentioned by old-school film critics in the same breath as, say, <a href=\"/name/nm0000009?ref_=nmls_mkdn\">Richard Burton</a> or <a href=\"/name/nm0000027?ref_=nmls_mkdn\">Alec Guinness</a>; however, movie fans worldwide have been flocking to see Stallone's films for over 30 years, making \"Sly\" one of ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000701/?ref_=nmls_pst\"> <img alt=\"Kate Winslet\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BODgzMzM2NTE0Ml5BMl5BanBnXkFtZTcwMTcyMTkyOQ@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">14. </span>\n" +
                "<a href=\"/name/nm0000701?ref_=nmls_hd\"> Kate Winslet\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0338013/?ref_=nmls_kf\"> Eternal Sunshine of the Spotless Mind\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Ask Kate Winslet what she likes about any of her characters, and the word \"ballsy\" is bound to pop up at least once. The British actress has made a point of eschewing straightforward pretty-girl parts in favor of more devilish damsels; as a result, she's built an eclectic resume that runs the gamut...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000288/?ref_=nmls_pst\"> <img alt=\"Christian Bale\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTkxMzk4MjQ4MF5BMl5BanBnXkFtZTcwMzExODQxOA@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">15. </span>\n" +
                "<a href=\"/name/nm0000288?ref_=nmls_hd\"> Christian Bale\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0468569/?ref_=nmls_kf\"> The Dark Knight\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Christian Charles Philip Bale was born in Pembrokeshire, Wales, UK on January 30, 1974, to English parents Jennifer \"Jenny\" (James) and <a href=\"/name/nm1186739?ref_=nmls_mkdn\">David Bale</a>. His mother was a circus performer and his father, who was born in South Africa, was a commercial pilot. The family lived in different countries ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000151/?ref_=nmls_pst\"> <img alt=\"Morgan Freeman\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTc0MDMyMzI2OF5BMl5BanBnXkFtZTcwMzM2OTk1MQ@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">16. </span>\n" +
                "<a href=\"/name/nm0000151?ref_=nmls_hd\"> Morgan Freeman\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0114369/?ref_=nmls_kf\"> Se7en\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    With an authoritative voice and calm demeanor, this ever popular American actor has grown into one of the most respected figures in modern US cinema. Morgan was born on June 1, 1937 in Memphis, Tennessee, to Mayme Edna (Revere), a teacher, and Morgan Porterfield Freeman, a barber. The young Freeman...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000206/?ref_=nmls_pst\"> <img alt=\"Keanu Reeves\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNjUxNDcwMTg4Ml5BMl5BanBnXkFtZTcwMjU4NDYyOA@@._V1_UY209_CR10,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">17. </span>\n" +
                "<a href=\"/name/nm0000206?ref_=nmls_hd\"> Keanu Reeves\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0133093/?ref_=nmls_kf\"> The Matrix\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Keanu Charles Reeves, whose first name means \"cool breeze over the mountains\" in Hawaiian, was born September 2, 1964 in Beirut, Lebanon. He is the son of <a href=\"/name/nm0852983?ref_=nmls_mkdn\">Patricia Taylor</a>, a showgirl and costume designer, and Samuel Nowlin Reeves, a geologist. Keanu's father was born in Hawaii, of British, ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000115/?ref_=nmls_pst\"> <img alt=\"Nicolas Cage\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjUxMjE4MTQxMF5BMl5BanBnXkFtZTcwNzc2MDM1NA@@._V1_UY209_CR6,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">18. </span>\n" +
                "<a href=\"/name/nm0000115?ref_=nmls_hd\"> Nicolas Cage\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0119094/?ref_=nmls_kf\"> Face/Off\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Nicolas Cage was born Nicolas Kim Coppola in Long Beach, California, the son of comparative literature professor <a href=\"/name/nm1692024?ref_=nmls_mkdn\">August Coppola</a> (whose brother is director <a href=\"/name/nm0000338?ref_=nmls_mkdn\">Francis Ford Coppola</a>) and dancer/choreographer Joy Vogelsang. He is of Italian (father) and Polish, German, and English (mother) descent. Cage ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0413168/?ref_=nmls_pst\"> <img alt=\"Hugh Jackman\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNDExMzIzNjk3Nl5BMl5BanBnXkFtZTcwOTE4NDU5OA@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">19. </span>\n" +
                "<a href=\"/name/nm0413168?ref_=nmls_hd\"> Hugh Jackman\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1707386/?ref_=nmls_kf\"> Les Misérables\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Hugh Michael Jackman is an Australian actor, singer, multi-instrumentalist, dancer and producer. Jackman has won international recognition for his roles in major films, notably as superhero, period, and romance characters. He is best known for his long-running role as Wolverine in the X-Men film ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0001570/?ref_=nmls_pst\"> <img alt=\"Edward Norton\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTYwNjQ5MTI1NF5BMl5BanBnXkFtZTcwMzU5MTI2Mw@@._V1_UY209_CR11,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">20. </span>\n" +
                "<a href=\"/name/nm0001570?ref_=nmls_hd\"> Edward Norton\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0171433/?ref_=nmls_kf\"> Keeping the Faith\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    American actor, filmmaker and activist Edward Harrison Norton was born on August 18, 1969, in Boston, Massachusetts, and was raised in Columbia, Maryland.<br><br>His mother, Lydia Robinson \"Robin\" (Rouse), was a foundation executive and teacher of English, and a daughter of famed real estate developer ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000246/?ref_=nmls_pst\"> <img alt=\"Bruce Willis\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjA0MjMzMTE5OF5BMl5BanBnXkFtZTcwMzQ2ODE3Mw@@._V1_UY209_CR19,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">21. </span>\n" +
                "<a href=\"/name/nm0000246?ref_=nmls_hd\"> Bruce Willis\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0095016/?ref_=nmls_kf\"> Die Hard\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Actor and musician Bruce Willis is well known for playing wisecracking or hard-edged characters, often in spectacular action films. Collectively, he has appeared in films that have grossed in excess of $2.5 billion USD, placing him in the top ten stars in terms of box office receipts.<br><br>Walter Bruce ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000158/?ref_=nmls_pst\"> <img alt=\"Tom Hanks\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQ2MjMwNDA3Nl5BMl5BanBnXkFtZTcwMTA2NDY3NQ@@._V1_UY209_CR2,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">22. </span>\n" +
                "<a href=\"/name/nm0000158?ref_=nmls_hd\"> Tom Hanks\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Producer <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0162222/?ref_=nmls_kf\"> Cast Away\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Thomas Jeffrey Hanks was born in Concord, California, to Janet Marylyn (Frager), a hospital worker, and Amos Mefford Hanks, an itinerant cook. His mother's family, originally surnamed \"Fraga\", was entirely Portuguese, while his father was of mostly English ancestry. Tom grew up in what he has ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000234/?ref_=nmls_pst\"> <img alt=\"Charlize Theron\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTk5Mzc4ODU0Ml5BMl5BanBnXkFtZTcwNjU1NTI0Mw@@._V1_UY209_CR8,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">23. </span>\n" +
                "<a href=\"/name/nm0000234?ref_=nmls_hd\"> Charlize Theron\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Producer <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0340855/?ref_=nmls_kf\"> Monster\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Charlize Theron was born in Benoni, a city in the greater Johannesburg area, in South Africa, the only child of <a href=\"/name/nm3105211?ref_=nmls_mkdn\">Gerda Theron</a> (née Maritz) and Charles Theron. She was raised on a farm outside the city. Theron is of Afrikaner (Dutch, with some French Huguenot and German) descent, and Afrikaner ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000226/?ref_=nmls_pst\"> <img alt=\"Will Smith\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNTczMzk1MjU1MV5BMl5BanBnXkFtZTcwNDk2MzAyMg@@._V1_UY209_CR2,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">24. </span>\n" +
                "<a href=\"/name/nm0000226?ref_=nmls_hd\"> Will Smith\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Music_department <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0098800/?ref_=nmls_kf\"> The Fresh Prince of Bel-Air\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Willard Carroll \"Will\" Smith, Jr. (born September 25, 1968) is an American actor, comedian, producer, rapper, and songwriter. He has enjoyed success in television, film, and music. In April 2007, Newsweek called him \"the most powerful actor in Hollywood\". Smith has been nominated for five Golden ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000125/?ref_=nmls_pst\"> <img alt=\"Sean Connery\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMzcwNTM4MzctYjQzMi00NTA2LTljYWItNTYzNmE1MTYxN2RlXkEyXkFqcGdeQXVyMDI2NDg0NQ@@._V1_UY209_CR11,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">25. </span>\n" +
                "<a href=\"/name/nm0000125?ref_=nmls_hd\"> Sean Connery\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0117500/?ref_=nmls_kf\"> The Rock\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    The tall, handsome and muscular Scottish actor Sean Connery is best known for being the original actor to portray the character of James Bond in the hugely successful movie franchise, starring in seven films between 1962 and 1983. Some believed that such a career-defining role might leave him ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0461136/?ref_=nmls_pst\"> <img alt=\"Keira Knightley\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTYwNDM0NDA3M15BMl5BanBnXkFtZTcwNTkzMjQ3OA@@._V1_UY209_CR4,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">26. </span>\n" +
                "<a href=\"/name/nm0461136?ref_=nmls_hd\"> Keira Knightley\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0325980/?ref_=nmls_kf\"> Pirates of the Caribbean: The Curse of the Black Pearl\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Keira Christina Knightley was born March 26, 1985 in the South West Greater London suburb of Richmond. She is the daughter of actor <a href=\"/name/nm0461139?ref_=nmls_mkdn\">Will Knightley</a> and actress turned playwright <a href=\"/name/nm0531930?ref_=nmls_mkdn\">Sharman Macdonald</a>. An older brother, <a href=\"/name/nm1045816?ref_=nmls_mkdn\">Caleb Knightley</a>, was born in 1979. Her father is English, while her Scottish-born ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0004874/?ref_=nmls_pst\"> <img alt=\"Vin Diesel\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjExNzA4MDYxN15BMl5BanBnXkFtZTcwOTI1MDAxOQ@@._V1_UY209_CR5,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">27. </span>\n" +
                "<a href=\"/name/nm0004874?ref_=nmls_hd\"> Vin Diesel\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt2015381/?ref_=nmls_kf\"> Guardians of the Galaxy\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Vin Diesel was born Mark Sinclair in Alameda County, California, along with his fraternal twin brother, <a href=\"/name/nm0898731?ref_=nmls_mkdn\">Paul Vincent</a>. He was raised by his astrologer/psychologist mother, Delora Sherleen (Sinclair), and adoptive father, Irving H. Vincent, an acting instructor and theatre manager, in an artists' ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000354/?ref_=nmls_pst\"> <img alt=\"Matt Damon\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTM0NzYzNDgxMl5BMl5BanBnXkFtZTcwMDg2MTMyMw@@._V1_UY209_CR8,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">28. </span>\n" +
                "<a href=\"/name/nm0000354?ref_=nmls_hd\"> Matt Damon\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0119217/?ref_=nmls_kf\"> Good Will Hunting\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Matthew Paige Damon was born on October 8, 1970, in Boston, Massachusetts, to <a href=\"/name/nm1497320?ref_=nmls_mkdn\">Kent Damon</a>, a stockbroker, realtor and tax preparer, and <a href=\"/name/nm1497472?ref_=nmls_mkdn\">Nancy Carlsson-Paige</a>, an early childhood education professor at Lesley University. Matt has an older brother, Kyle, a sculptor. His father was of English and ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000152/?ref_=nmls_pst\"> <img alt=\"Richard Gere\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTI2NDQ2OTY4M15BMl5BanBnXkFtZTYwNTYyNjc4._V1_UY209_CR3,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">29. </span>\n" +
                "<a href=\"/name/nm0000152?ref_=nmls_hd\"> Richard Gere\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0299658/?ref_=nmls_kf\"> Chicago\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Humanitarian and actor Richard Gere was born on August 31, 1949, in Philadelphia, the second of five children of Doris Ann (Tiffany), a homemaker, and Homer George Gere, an insurance salesman, both Mayflower descendants. Richard started early as a musician, playing a number of instruments in high ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0001876/?ref_=nmls_pst\"> <img alt=\"Catherine Zeta-Jones\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BZGE4MzUwMTYtYTFmNi00ZTRhLThjNDEtY2FlMTgyZmVmYTQwXkEyXkFqcGdeQXVyODczMzQ3NjY@._V1_UY209_CR54,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">30. </span>\n" +
                "<a href=\"/name/nm0001876?ref_=nmls_hd\"> Catherine Zeta-Jones\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0299658/?ref_=nmls_kf\"> Chicago\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Catherine Zeta-Jones was born September 25, 1969 in Swansea, Wales (and raised in the nearby town of Mumbles), the only daughter of Patricia (nee Fair) and David James \"Dai\" Jones, who formerly owned a sweet factory. She attended Dumbarton House School (Swansea). Her father (the son of Bertram (...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0654110/?ref_=nmls_pst\"> <img alt=\"Clive Owen\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjA4MzAyOTc5Ml5BMl5BanBnXkFtZTcwOTQ5NzEzMg@@._V1_UY209_CR9,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">31. </span>\n" +
                "<a href=\"/name/nm0654110?ref_=nmls_hd\"> Clive Owen\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0376541/?ref_=nmls_kf\"> Closer\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    British actor Clive Owen is one of a handful of stars who, though he is best known for his art house films, can handle more mainstream films with equal measures of grace and skill. Owen is typically cast as characters whose primary traits are a balance of physical strength, intellect, conflicting ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000154/?ref_=nmls_pst\"> <img alt=\"Mel Gibson\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNTUzOTMwNTM0OV5BMl5BanBnXkFtZTcwNDQwMTUxMw@@._V1_UY209_CR6,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">32. </span>\n" +
                "<a href=\"/name/nm0000154?ref_=nmls_hd\"> Mel Gibson\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0112573/?ref_=nmls_kf\"> Braveheart\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Mel Columcille Gerard Gibson was born January 3, 1956 in Peekskill, New York, USA, as the sixth of eleven children of <a href=\"/name/nm1585176?ref_=nmls_mkdn\">Hutton Gibson</a>, a railroad brakeman, and Anne Patricia (Reilly) Gibson (who died in December of 1990). His mother was Irish, from County Longford, while his American-born father is ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000123/?ref_=nmls_pst\"> <img alt=\"George Clooney\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjEyMTEyOTQ0MV5BMl5BanBnXkFtZTcwNzU3NTMzNw@@._V1_UY209_CR7,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">33. </span>\n" +
                "<a href=\"/name/nm0000123?ref_=nmls_hd\"> George Clooney\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0465538/?ref_=nmls_kf\"> Michael Clayton\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    George Timothy Clooney was born on May 6, 1961, in Lexington, Kentucky, to Nina Bruce (née Warren), a former beauty pageant queen, and <a href=\"/name/nm0167040?ref_=nmls_mkdn\">Nick Clooney</a>, a former anchorman and television host (who was also the brother of singer <a href=\"/name/nm0167041?ref_=nmls_mkdn\">Rosemary Clooney</a>). He has Irish, English, and German ancestry. Clooney ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000197/?ref_=nmls_pst\"> <img alt=\"Jack Nicholson\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQ3OTY0ODk0M15BMl5BanBnXkFtZTYwNzE4Njc4._V1_UY209_CR5,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">34. </span>\n" +
                "<a href=\"/name/nm0000197?ref_=nmls_hd\"> Jack Nicholson\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0071315/?ref_=nmls_kf\"> Chinatown\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Jack Nicholson, an American actor, producer, director and screenwriter, is a three-time Academy Award winner and twelve-time nominee. Nicholson is also notable for being one of two actors - the other being <a href=\"/name/nm0000323?ref_=nmls_mkdn\">Michael Caine</a> - who have received an Oscar nomination in every decade from the 1960s through ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0424060/?ref_=nmls_pst\"> <img alt=\"Scarlett Johansson\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTM3OTUwMDYwNl5BMl5BanBnXkFtZTcwNTUyNzc3Nw@@._V1_UY209_CR16,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">35. </span>\n" +
                "<a href=\"/name/nm0424060?ref_=nmls_hd\"> Scarlett Johansson\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0335266/?ref_=nmls_kf\"> Lost in Translation\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Scarlett Johansson was born in New York City. Her mother, <a href=\"/name/nm0424012?ref_=nmls_mkdn\">Melanie Sloan</a>, is from a Jewish family from the Bronx, and her father, <a href=\"/name/nm0423984?ref_=nmls_mkdn\">Karsten Johansson</a>, is a Danish-born architect, from Copenhagen. She has a sister, <a href=\"/name/nm0424089?ref_=nmls_mkdn\">Vanessa Johansson</a>, who is also an actress, a brother, Adrian, a twin brother, <a href=\"/name/nm0423956?ref_=nmls_mkdn\">Hunter </a>...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0362766/?ref_=nmls_pst\"> <img alt=\"Tom Hardy\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQ3ODEyNjA4Nl5BMl5BanBnXkFtZTgwMTE4ODMyMjE@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">36. </span>\n" +
                "<a href=\"/name/nm0362766?ref_=nmls_hd\"> Tom Hardy\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1375666/?ref_=nmls_kf\"> Inception\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    With his breakthrough performance as Eames in <a href=\"/name/nm0634240?ref_=nmls_mkdn\">Christopher Nolan</a>'s sci-fi thriller <a href=\"/title/tt1375666?ref_=nmls_mkdn\">Inception</a> (2010), English actor Tom Hardy has been brought to the attention of mainstream audiences worldwide. However, the versatile actor has been steadily working on both stage and screen since his television debut...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000375/?ref_=nmls_pst\"> <img alt=\"Robert Downey Jr.\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">37. </span>\n" +
                "<a href=\"/name/nm0000375?ref_=nmls_hd\"> Robert Downey Jr.\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0371746/?ref_=nmls_kf\"> Iron Man\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Robert Downey Jr. has evolved into one of the most respected actors in Hollywood. With an amazing list of credits to his name, he has managed to stay new and fresh even after over four decades in the business.<br><br>Downey was born April 4, 1965 in Manhattan, New York, the son of writer, director and ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0089217/?ref_=nmls_pst\"> <img alt=\"Orlando Bloom\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjE1MDkxMjQ3NV5BMl5BanBnXkFtZTcwMzQ3Mjc4MQ@@._V1_UY209_CR6,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">38. </span>\n" +
                "<a href=\"/name/nm0089217?ref_=nmls_hd\"> Orlando Bloom\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0120737/?ref_=nmls_kf\"> The Lord of the Rings: The Fellowship of the Ring\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Orlando Jonathan Blanchard Bloom was born in Canterbury, Kent, England on January 13, 1977. His mother, Sonia Constance Josephine (Copeland), was born in Kolkata, India, to an English family then-resident there. The man he first knew as his father, Harry Bloom, was a legendary political activist ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0005212/?ref_=nmls_pst\"> <img alt=\"Ian McKellen\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQ2MjgyNjk3MV5BMl5BanBnXkFtZTcwNTA3NTY5Mg@@._V1_UY209_CR7,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">39. </span>\n" +
                "<a href=\"/name/nm0005212?ref_=nmls_hd\"> Ian McKellen\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0120737/?ref_=nmls_kf\"> The Lord of the Rings: The Fellowship of the Ring\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Widely regarded as one of greatest stage and screen actors both in his native Great Britain and internationally, twice nominated for the Oscar and recipient of every major theatrical award in the UK and US, Ian Murray McKellen was born on May 25, 1939 in Burnley, Lancashire, England, to Margery ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000104/?ref_=nmls_pst\"> <img alt=\"Antonio Banderas\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTUyOTQ3NTYyNF5BMl5BanBnXkFtZTcwMTY2NjIzNQ@@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">40. </span>\n" +
                "<a href=\"/name/nm0000104?ref_=nmls_hd\"> Antonio Banderas\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0120746/?ref_=nmls_kf\"> The Mask of Zorro\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Antonio Banderas, one of Spain's most famous faces, was a soccer player until breaking his foot at the age of fourteen; he is now an international film star known for playing Zorro in the eponymous film series.<br><br>He was born José Antonio Domínguez Banderas on August 10, 1960, in Málaga, Andalusia, ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0001602/?ref_=nmls_pst\"> <img alt=\"Guy Pearce\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTgyNzc2NzY3Nl5BMl5BanBnXkFtZTgwNTMzMzAwMjE@._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">41. </span>\n" +
                "<a href=\"/name/nm0001602?ref_=nmls_hd\"> Guy Pearce\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0209144/?ref_=nmls_kf\"> Memento\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Guy Edward Pearce was born 5 October, 1967 in Cambridgeshire, England, UK to Margaret Anne and Stuart Graham Pearce. His father was born in Auckland, New Zealand, to English and Scottish parents, while Guy's mother is English. Pearce and his family initially traveled to Australia for two years, ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000168/?ref_=nmls_pst\"> <img alt=\"Samuel L. Jackson\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTQ1NTQwMTYxNl5BMl5BanBnXkFtZTYwMjA1MzY1._V1_UX140_CR0,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">42. </span>\n" +
                "<a href=\"/name/nm0000168?ref_=nmls_hd\"> Samuel L. Jackson\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0110912/?ref_=nmls_kf\"> Pulp Fiction\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Samuel L. Jackson is an American producer and highly prolific actor, having appeared in over 100 films, including <a href=\"/title/tt0112864?ref_=nmls_mkdn\">Die Hard with a Vengeance</a> (1995), <a href=\"/title/tt0217869?ref_=nmls_mkdn\">Unbreakable</a> (2000), <a href=\"/title/tt0162650?ref_=nmls_mkdn\">Shaft</a> (2000), <a href=\"/title/tt0227984?ref_=nmls_mkdn\">Formula 51</a> (2001), <a href=\"/title/tt0462200?ref_=nmls_mkdn\">Black Snake Moan</a> (2006), <a href=\"/title/tt0417148?ref_=nmls_mkdn\">Snakes on a Plane</a> (2006), and the Star Wars prequel trilogy (1999-2005), ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000113/?ref_=nmls_pst\"> <img alt=\"Sandra Bullock\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTI5NDY5NjU3NF5BMl5BanBnXkFtZTcwMzQ0MTMyMw@@._V1_UY209_CR1,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">43. </span>\n" +
                "<a href=\"/name/nm0000113?ref_=nmls_hd\"> Sandra Bullock\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Producer <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1041829/?ref_=nmls_kf\"> The Proposal\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Sandra Annette Bullock was born in Arlington, a Virginia suburb of Washington, D.C. Her mother, <a href=\"/name/nm1184542?ref_=nmls_mkdn\">Helga Bullock</a> (née Helga Mathilde Meyer), was a German opera singer. Her father, John W. Bullock, was an American voice teacher, who was born in Alabama, of German descent. Sandra grew up on the road ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000212/?ref_=nmls_pst\"> <img alt=\"Meg Ryan\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTgzOTI0NzI3OV5BMl5BanBnXkFtZTgwODIyMzUzMDI@._V1_UY209_CR1,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">44. </span>\n" +
                "<a href=\"/name/nm0000212?ref_=nmls_hd\"> Meg Ryan\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0098635/?ref_=nmls_kf\"> When Harry Met Sally...\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Blond-haired, blue-eyed with an effervescent personality, Meg Ryan was born Margaret Mary Emily Hyra in Fairfield, Connecticut, to Susan (Duggan), an English teacher and one-time actress, and Harry Hyra, a math teacher. She is of Ruthenian, Polish, Irish, and German ancestry (\"Hyra\" is a Ruthenian ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm1083271/?ref_=nmls_pst\"> <img alt=\"Megan Fox\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTc5MjgyMzk4NF5BMl5BanBnXkFtZTcwODk2OTM4Mg@@._V1_UY209_CR3,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">45. </span>\n" +
                "<a href=\"/name/nm1083271?ref_=nmls_hd\"> Megan Fox\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0418279/?ref_=nmls_kf\"> Transformers\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Megan Fox was born Megan Denise Fox on May 16, 1986 in Oak Ridge, Tennessee to Gloria Darlene Tonachio (born: Gloria Darlene Cisson), a real estate manager &amp; Franklin Thomas Fox, a parole officer; she was raised in Rockwood, Tennessee during her early childhood. She began her drama &amp; dance training...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000173/?ref_=nmls_pst\"> <img alt=\"Nicole Kidman\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTk1MjM5NDg4MF5BMl5BanBnXkFtZTcwNDg1OTQ4Nw@@._V1_UY209_CR7,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">46. </span>\n" +
                "<a href=\"/name/nm0000173?ref_=nmls_hd\"> Nicole Kidman\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0203009/?ref_=nmls_kf\"> Moulin Rouge!\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Elegant blonde Nicole Kidman, known as one of Hollywood's top Australian imports, was actually born in Honolulu, Hawaii, while her Australian parents were there on educational visas.<br><br>Kidman is the daughter of Janelle Ann (Glenny), a nursing instructor, and Antony David Kidman, a biochemist and ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0124930/?ref_=nmls_pst\"> <img alt=\"Gerard Butler\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjE4NDMwMzc4Ml5BMl5BanBnXkFtZTcwMDg4Nzg4Mg@@._V1_UY209_CR4,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">47. </span>\n" +
                "<a href=\"/name/nm0124930?ref_=nmls_hd\"> Gerard Butler\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0293508/?ref_=nmls_kf\"> The Phantom of the Opera\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Gerard James Butler was born in Paisley, Scotland, to Margaret and Edward Butler, a bookmaker. His family is of Irish origin. Gerard spent some of his very early childhood in Montreal, Quebec, but was mostly raised, along with his older brother and sister, in his hometown of Paisley. His parents ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0048932/?ref_=nmls_pst\"> <img alt=\"Simon Baker\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMjgwODNlOGMtODMxMC00ZGZlLWI3YTQtZjkzMmIwOGZiNzlhXkEyXkFqcGdeQXVyMTA0MDM3MTI@._V1_UY209_CR116,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">48. </span>\n" +
                "<a href=\"/name/nm0048932?ref_=nmls_hd\"> Simon Baker\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actor <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1615147/?ref_=nmls_kf\"> Margin Call\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Simon Baker was first recognized in 1992, when he received Australia's prestigious Logie award for Most Popular New Talent. Upon relocating to Los Angeles with his family, Baker was immediately cast in the Academy Award winning film <a href=\"/title/tt0119488?ref_=nmls_mkdn\">L.A. Confidential</a> (1997).                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0000139/?ref_=nmls_pst\"> <img alt=\"Cameron Diaz\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BMTkxNTI5NzM4MV5BMl5BanBnXkFtZTcwMTI3ODY3Mg@@._V1_UY209_CR1,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">49. </span>\n" +
                "<a href=\"/name/nm0000139?ref_=nmls_hd\"> Cameron Diaz\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt0129387/?ref_=nmls_kf\"> There's Something About Mary\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    A tall, strikingly attractive blue-eyed natural blonde, Cameron Diaz was born in 1972 in San Diego, the daughter of a Cuban-American father and a German mother. Self described as \"adventurous, independent and a tough kid,\" Cameron left home at 16 and for the next 5 years lived in such varied ...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"lister-item mode-detail\">\n" +
                "        <div class=\"lister-item-image\">\n" +
                "<a href=\"/name/nm0001337/?ref_=nmls_pst\"> <img alt=\"Katherine Heigl\" height=\"209\" src=\"https://m.media-amazon.com/images/M/MV5BNTM0NDEwMjA1MV5BMl5BanBnXkFtZTcwMzQxMDI3Mg@@._V1_UY209_CR1,0,140,209_AL_.jpg\" width=\"140\">\n" +
                "</a>        </div>\n" +
                "        <div class=\"lister-item-content\">\n" +
                "            <h3 class=\"lister-item-header\">\n" +
                "                <span class=\"lister-item-index unbold text-primary\">50. </span>\n" +
                "<a href=\"/name/nm0001337?ref_=nmls_hd\"> Katherine Heigl\n" +
                "</a>            </h3>\n" +
                "                <p class=\"text-muted text-small\">\n" +
                "                        Actress <span class=\"ghost\">|</span>\n" +
                "<a href=\"/title/tt1598828/?ref_=nmls_kf\"> One for the Money\n" +
                "</a>                </p>\n" +
                "                <p>\n" +
                "    Katherine Marie Heigl was born on November 24, 1978 in Washington, D.C., to <a href=\"/name/nm2784694?ref_=nmls_mkdn\">Nancy Heigl</a> (née Engelhardt), a personnel manager, and Paul Heigl, an accountant and executive. Her father is of German/Swiss-German and Irish descent, and her mother is of German ancestry. A short time after her birth, the...                </p>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "    </div>";
    }

    public void checkAns(View view){
        int k=locationOfCorrectAns;
        if(view.getTag().toString().equals(Integer.toString(k))){
            Toast.makeText(getApplicationContext(),"Correct !!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Worng, It was "+nameurl.get(ChosenCeleb),Toast.LENGTH_SHORT).show();
        }

        newQuestion();
    }

    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url=new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream input=connection.getInputStream();
                Bitmap mybitmap= BitmapFactory.decodeStream(input);
                return mybitmap;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public void newQuestion(){

        Random rand=new Random();
        ChosenCeleb=rand.nextInt(nameurl.size());
        ImageDownloader imgTask=new ImageDownloader();
        Bitmap celeb= null;
        try {
            celeb = imgTask.execute(imgurl.get(ChosenCeleb)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(celeb);
        locationOfCorrectAns=rand.nextInt(4);
        int incorrectAns=0;
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAns){
                answers[i]=nameurl.get(ChosenCeleb);
            }else {
                incorrectAns=rand.nextInt(nameurl.size());

                while (incorrectAns==ChosenCeleb){
                    incorrectAns=rand.nextInt(nameurl.size());
                }
                answers[i]=nameurl.get(incorrectAns);
            }
        }
        button1.setText(answers[0]);
        button2.setText(answers[1]);
        button3.setText(answers[2]);
        button4.setText(answers[3]);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        strings();
        Pattern p=Pattern.compile("src=\"(.*?)\" width");
        Matcher m=p.matcher(name);
        while(m.find()){
            imgurl.add(m.group(1));
        }

        Pattern p1=Pattern.compile("img alt=\"(.*?)\"");
        Matcher m1=p1.matcher(name);
        while(m1.find()){
            nameurl.add(m1.group(1));
        }
        newQuestion();
    }
}

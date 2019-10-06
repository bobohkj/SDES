import java.sql.SQLOutput;
import java.util.HashMap;

public class Homework1 {
    public String encOfCTC(String plaintext, int[] key){
        StringBuilder sb=new StringBuilder();
        int idx=0;
        for (int i=0;i<plaintext.length();i++){
            sb.append(transposition(plaintext.charAt(i),key[idx]));
            idx++;
            if (idx>=key.length) idx=0;
        }
        return sb.toString();
    }

    public String decOfCTC(String plaintext,int[] key){
        int[] newKey=new int[key.length];
        for (int i=0;i<key.length;i++){
            newKey[i]=-key[i];
        }
        return encOfCTC(plaintext,newKey);
    }

    private char transposition(char pchar, int key){
        char res=(char)(pchar+key);
        while (res-'z'>0) res-=26;
        while ('a'-res>0) res+=26;
        return res;
    }

    public int[] frequencyCalculator(String text,int size,char start){
        int[] result=new int[size];
        for (int i=0;i<text.length();i++){
            result[text.charAt(i)-start]++;
        }
        return result;
    }

    public void frequencyCalculator(String text,HashMap<Character,Integer> map){
        for(int i=0;i<text.length();i++){
            map.put(text.charAt(i),map.getOrDefault(text.charAt(i),0)+1);
        }
    }

    public void showOrder(){
        for (int i=0;i<26;i++){
            System.out.print((char)('+'+i));
        }
    }

    public void showFrequency(String order,HashMap<Character,Integer> map){
        for (char c:order.toCharArray()){
            System.out.println(c+"="+map.get(c)+",");
        }
    }

    public String decArbiraryMap(String ciphertext, HashMap<Character,Character> map){
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<ciphertext.length();i++){
            sb.append(map.getOrDefault(ciphertext.charAt(i),'_'));
        }
        return sb.toString();
    }

    public void createArbitraryMapping(HashMap<Character,Character> map){
        map.put('+','a');
        map.put('_','b');
        map.put('*','c');
        map.put('&','d');
        map.put('@','e');
        map.put('=','f');
        map.put('^','g');
        map.put('c','h');
        map.put('%','i');
        map.put('z','j');
        map.put('#','k');
        map.put('\'','l');
        map.put('~','m');
        map.put('!','n');
        map.put(':','o');
        map.put('}','p');
        map.put(')','q');
        map.put('(','r');
        map.put('`','s');
        map.put('?','t');
        map.put('>','u');
        map.put('<','v');
        map.put('/','w');
        map.put('|','x');
        map.put('.','y');
        map.put(' ','z');



    }

    public String[] splitIntoSets(int numberOfSets, String plaintext){
        String[] results=new String[numberOfSets];
        StringBuilder[] sets=new StringBuilder[numberOfSets];
        for(int i=0;i<numberOfSets;i++){
            sets[i]=new StringBuilder();
        }
        for (int j=0;j<plaintext.length();j++){
            sets[j%numberOfSets].append(plaintext.charAt(j));
        }
        for(int i=0;i<numberOfSets;i++){
            results[i]=sets[i].toString();
        }
        return results;
    }

    private String mergeText(String[] sets){
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<sets[0].length();i++){
            for (int j=0;j<sets.length;j++){
                if (i>=sets[j].length()) continue;
                sb.append(sets[j].charAt(i));
            }
        }
        return sb.toString();
    }

    public static void question4(){
        String ciphertext4="loqfmkhvnvlpkyiloigaszzrgwpdrhxywzefpvgijumgwgbzpijlxbvllzfjgybuiflelsjrbvqwzbvqwhvqslometmitvgsaqbrkymsyklbbhazkhwkhenzwvnfxgyqrwvlvvkjhbvrytwgmguxvglbzrwlhzfefkxesvbkrvkhtysxaprqtharhguqypwniypqvjgeaumqtjvxrvlfwswgugglsaenwzhkxivhvqjwukrhtfkemepvnpkhxcejlvgpqdweoauossjuwexzrweiszuhvvlzbykkqpxsawewzpxfsffxvglbzrwwubrvlhqaqwubjekcqpxatqmivympifatlmfhunngykbvhvznxwzmpyjpblfjliplswxnvwubycauzrxssqnxavvssjapryhjwzmfnkbqwkgglwpvgijcqravpovxsskbtalabjmuzrpwharhxptzwhlzfsfhtsmfhvpmsslnxsvvrrllzgeauurrlpvqykazlrgaiopwzmzeasacekzebvvziahgaprvaunbvehbvsfhzrtgybrhgumuyfkzrhllznfqamfsxkigeauiypzhdrfwlvfxgsmaetvcgjgbzgcyponfqamflscmoiwuunhwwcopajabjsyunmfzbeistxhfdpknxavvfazpkuejlvrzwyzrxajmaxsiwhxkjwyhauoyikzmfxsitvwzllzivpibzwyiegsumwsmyvnpazbvgkjzhtdlanvwwcopazpvrykizeypvthsaisvgtbumkazbzwhaaiozxntwyanrvumjwuoiarwsapedsbuikawyifzwacvhbnaaaptvwhbeidpauefkjnvwsgpsfjmnpwkkbrllucxxvzusdsgjsgkbuiflelsjrbvqwzqfwzhzvrybvplsyqgetsmpsetmaxktiqiaumzeasaoildmrrlomrblyibvvpvnvasgfyujmfwxbtcvgkcpijzkbxlycqmfhvqwgugfxmkqbggjpnmjdwzefhultszknplomjekoqaklvvcskamztzhavdwzbuelycqmfoiqyfrqahovzqwxvzcvgtqaifaipxjlafhaympxgyiahhywqyulznryltvrsqwymwhajidsifjgyiamdskbrulqiivwtnrlvjhmdkippwvxnxjhubzaliesmulwsdpmgmelunksgqaikzizjjphrpdamnwwzbuikldrrevagsmaznkwvcfxzpvtwoltrejumqjjvuglwzwaczhkxjjphrpdkmppaumqxgymftgulgsibmfxavvfjjvuaelpwaedymimwdwapaumnfgbbglwwzbtjpmgcgmbeexmqpoauovrkawyifnwbhkmwexzlxhvhvarsxdzvxauoovwhbupwzanvlpkyikhjbylzkhxlsmoylabuelpnvxaudbpnllnrqvbuijpvqykazlagbtqfwjwawakmeivdmypopbumfaprfgbvqejpmfsxuweqssebvcwtngwzvvtauonpkvlrgdpvvryawpsetmaxepkuewskvihsgnrvizbsczjnvflabjlomgmelanrvciemwagfedlffxwkunrsdiflauogsfwwfxwkqgsjymftgulfxzhbglwwicijkwrwfvbcijtqgvwwwexwyagstymnolomyeopvcyjzcvxgmagsjpmfawumiijlvpsmyitisugbrwawfxwhtqsuburrlzvnxavvnpwjwasefiahtbavrwzarhaawekjlofgzumvhwyeemllavrsumzeasbbrsaqbrsszrzalebrdpvrlgdmiijdprrvvkhqwubfqsrmglwpzjeqpvgslomcytsqphgtivrgyieiklvgxgbajisymjmloqasmyzvkzaagsjlxbvlvvglwttrecznesejwztsuqrwsultsnlzaqwubnkwukvikhzrrgacaggtubrgcmeqsugqiuhlrwkbkupwhsflscmcvwzmaxwkvrakvztefphnxavvfaaapnaakmeefnmbjupzpyezbnrulaglsaknpdmwexzlugswemegazmwyvnurrldmnwklafisjpfilvnseuaavrvpdvhmhtycaubumkpvfxsukrxzlzrpwharsxkwpyelvgwohanrwcmaxloighwtiahwkkbzwyitisulglwpvssjtigmgujesmnpgxgsqtlloifwlpzeivkqfgmzavsfhjbylhpbwlvnyiypbvqsamvwkbmfxzhbnpkvenvjhvgivjwiijhorxzpavwsuwglwyiexajtrxzloesopvthvvagljligyfrvbafhbgeurmewzhdrfwlvgikaqaklomqixlvfikvnpsewiamwzbuelycagjpbvgssxnvlzwsxzlqaxwyvrxhvafmtsggsxpohvwvcglgdbbxsrmglwtlbafjgoijzmpyjpblipwmextycpikjpaialzjejumqxmlaqeqsiekwuigmguagellacijoicwuoqaegyzhwkpinvwaprparmycubtcvaaauikbotikamqrsaqbrkaigisjbbvkhzrkgpvtxgwzbfwawsmfkerecumfwwzqaedswssmybrgzuwysypmfwsplgvscqfwepbuwwuqbvklkhvaageikliegzlvtmflmeelazvtopzrxzlgjefabbofvejlsaknrtllbrwuwgsfsgvrlomrzwubbjsjgoijdiefmaixmflbvgohznwoltylwawyhllkurwdajsjslglwlifmwzbjeqawgecliaildweogmnglwpvgijumgmkdqglskqfxjpjhxwklrrahtbjklzimuligxsjsfgzumvijzivhsulfselwsxzlbnvylbrhuvucefpmfvwjmaxdfpnzwimrrzpbjmlolqskhbgeuraglsaieikpoamxpknrlsgyejnmepguorvdhagmfniahevzrwgwpvwlpknxwkbuefimssjlbuisabngczbltajiypqyiztmwbbehhzgmubtnvdldrplomawlvxglwfzrwmtmnxloiglanprvdldrpsulglwukbrlpvhijhucmfnccekpnglwhbgeurmewsymysgrqakxvzglwumgagysfiphkgtgpvgsxmivpmymfgzumvijzxrgmsigivaprelaipokbarqmsbvtdldrglvzfjgykvryaiekwaagsvlxysqhtysxaprmjkmsifzmfxzbaqmkjtbwauoglwpzpehhjvpaaqrwtlknyklbuisabngclzfazlzretvcgwsymhrcuwjrhvbrrlpiyxsyorxkjiahguwglauogsohzqxzlubjxzkurwpmewsplglwkigeklmzwlvqahajigiuoqaeazjrlaulglwtjhxaaacskzqopwawqmkncvwwaprggbvgvqvnbvanqahvvanrvvbuijhbgeuraumlympsjkprmyobfmfaprwwjwahibiexwywsxzpalisyixeehqeihvzgivklbwsabngczzbwwaerrlfbuvwlxrvulvgsnlzglwuczfwyzrggylrhauyhejlzssmybjslowhwsulnrvmqsxwlvnrvdmoehwtvgsaqbrsabngczqagjlifivaerrlfavbhlzpifabnvylbfwmmnrvwkitvwhbrvfbuoijvneihlighvvanxlhkxwldmaxquqaiguiiijhorqmsbvzwjbbvwkigxsjsfmfjzrekllnwvplziyhigxsjsfsxtweiloiasflphrvlzqktwahwauofmewtrelaiponlkgsjzxbwkpjyigyvbxkaigisjbbvkhzrtjvjnfdftbscpvtelhvhqtlzbjvpnsijlvgasfagsvpanfdlxnvlzweedswsxzlqaxwyvrxuvuzifamqtsbtzsuricilyqfggpviifawesxaprhgtivrfhurwqzbrqubzeifatlgzpmswupmaxazbnxlozrelzbbtvkwfmkvvrsxaprasfagsvvbuelhvqmovcyhatitmflagellipxgyajsmslnxlhkxvgbbvryzgfxwtanwoltylwawyhllkurwdajsjslglwhbgeurajsmsloievagixmmpxacmnkspvfxkoieivjwzqguaglwwcopajzrwgbzpikvvglwdmofmaxrshsmpsmsltsthkxxgaprwqzbrqgmxnvlpbvsfpvtxzlqaxwyvrxevkxehlbemkzctkwzbrhlowfioowuenlbuiaywjrhywgiuamqrwaebvcdqypuvvgmfbmgszhdrmfamerwaipgwzanxsrmqsouwsxzlmaxaymvrllzailpaaslnwvryawuehwmaggubrrvllzejaqaqurmncklkhvaagnhnvknxwhbnostivfwjihwwpbfeoowyitbvplgmvrxovzxwsullsmymaslnwvryawgeclqghgdvhrdlafcgbbnowkwjrsstglwjqegmpbfcgbknrlhsrhgdvnggtxnrqhvbvyhvvdsaqbrgyxnvlvnnkgcmerelvgfmagbyuhvgvwhtyclhsrhgdvglwpvgijumgekheusdlkbqebvvgsaqbrksqaokhzrxgvevhwzxeisknbvsntbfssigxsjsgskbkpiwkprxgslgiuovrakdwepvaprvwhzreuvccpwkwmifameetpbpmjjcvxkmzbqkhvsvsukvwuviysflbblguoxsfniahlvslssulbxzlzcpsjmfxzltnvylagrwaebvcsilijhbgeurafiwuabjsyictjviplauosmnlphrvymqktwanvwhvbvvlzbjehoamlblrwehtyijapnrlomoefkevhlokntsjqgclomyejnmfxlyiawaaxesnplrvkhvqmkwazefhorrgamqxatunxzlefzajmcvwzqqifawsxzlqagswahpswzbhmjbymfligmewmezsdqglhywcijklbwhywgiuaqbrkpvcpsjmzskaigxsjsfagbtqfwzbbthllvrlomvvlyipokomgsdkbrgzumjwovzyhovzfxuharwulvnvavaglwswfwgmcgmdpbvikhvqielztifjgfijcqpikymfydaqakxywzefpvgijumgxsrmqsoukbydkxesevbrxzlmfxsitvwztmaxgmuvpaaqnkjvccwsulcskzqopqhjeisrlbafvnfsupmgcohzaivtqplsltcelamewgukrsgmxymplzvqsnqaiqvcerwpoufgyarbuscqmfngbyxywztjvbrglpwafwjihwwfwhlscmasjlabyjjmfxgzpnvwaprvwzxbrkpjvpaaggskhnrkmhzqxzlqaxwyvrxxywzelaipokoifjsstrrdhztidfwawwydvgwwzbzakmewzlbbpvamplflefagytqmfaprwzvzgvmujnrcziahgaprvtbavrwzarwuvcyhkbageaukbrkplrvsitriuvvbqajtbwklavjlomvrllzaildmaxvveaefkbuiqswfxwwprqwyiyxjhvfeuaqbrsslnxshsnqspazgclilwmnorwllloylswakllzzsmaitikhzrrlhxestsmz";
        Homework1 homework1=new Homework1();
        String[] sets=homework1.splitIntoSets(5,ciphertext4);
        for (String set:sets){
            int[] freqs=homework1.frequencyCalculator(set,26,'a');
            int highest=Integer.MIN_VALUE;
            int idx=0;
            int shift=0;
            for (int  freq:freqs){
                if (highest<freq){
                    highest=freq;
                    shift=idx;
                }
                idx++;
            }
            System.out.println((char)('a'+shift)-'e');
            System.out.println("len="+set.length());
        }
        int[] keys=new int[]{18,7,8,13,4};
        for (int i=0;i<sets.length;i++){
            sets[i]=homework1.decOfCTC(sets[i],new int[]{keys[i]});
        }
        System.out.println(homework1.mergeText(sets));
    }

    public static void main(String[] args) {
//        String plaintext="theshadowofthemoonsweptacrosstheglobefromhongkongtothetexaspanhandleasarareannularsolareclipsebeganmondaymorninginasiaandtraversedthepacificthesunappearedasathinringbehindthemoontopeopleinanarrowpathalongthecenterofthetrackwhichbeganinsouthernchinaheavycloudsobscuredtheviewinhongkongbutresidentsoftokyoandothercitieswereabletogetaspectacularviewforaboutfourminutesaroundseventhirtytwoammondaysixthirtytwopmetsundayeventswereheldatschoolsandmuseumsinjapanwhilemanymorepeopletookintheunusualastronomicaleventathomeoronstreetcornersafterwhizzingacrossthepacifictheshadowemergedovernortherncaliforniaandsouthernoregonwherethousandsofpeopleattendedpartiestowatchtheeventthefirsttoappearintheunitedstatessincenineteenninetyfourexpertswarnedthathopefulviewersshouldnotpeerupattheskywithoutspecialviewingequipmentsincelookingatthesunwiththenakedeyecancauseblindnessderekralstonaprofessionalphotographersaidheusedaweldingfiltertocaptureadirectviewofeclipseinthefoothillsaboveorovillecaliforniahesharedthephotooncnnireportnotingtheratherslimswathoftheglobewhocouldseetheimpactoftheeclipseralstonsaidhewantedtoenabletherestoftheworldtoseehowclearitlookedtothoseofuswhowerefortunateenoughtoseeitthesliverofsunshinethentraveledsoutheastacrosscentralnevadasouthernutahandnorthernarizonaandthennewmexicoitpassedoveralbuquerquenewmexicoaboutseventhirtyfourpmninethirtyfourpmetbeforepeteringouteastoflubbocktexasaccordingtonasa";
//        int[] key=new int[]{1,3,5,2,7};
//        String plaintext2="theshadowofthemoonsweptacrosstheglobefromhongkongtothetexaspanhandleasarareannularsolareclipsebeganmondaymorninginasiaandtraversedthepacificthesunappearedasathinringbehindthemoontopeopleinanarrowpathalongthecenterofthetrackwhichbeganinsouthernchinaheavycloudsobscuredtheviewinhongkongbutresidentsoftokyoandothercitieswereabletogetaspectacularviewforaboutfourminutesaroundseventhirtytwoammondaysixthirtytwopmetsundayeventswereheldatschoolsandmuseumsinjapanwhilemanymorepeopletookintheunusualastronomicaleventathomeoronstreetcornersafterwhizzingacrossthepacifictheshadowemergedovernortherncaliforniaandsouthernoregonwherethousandsofpeopleattendedpartiestowatchtheeventthefirsttoappearintheunitedstatessincenineteenninetyfourexpertswarnedthathopefulviewersshouldnotpeerupattheskywithoutspecialviewingequipmentsincelookingatthesunwiththenakedeyecancauseblindnessderekralstonaprofessionalphotographersaidheusedaweldingfiltertocaptureadirectviewofeclipseinthefoothillsaboveorovillecaliforniahesharedthephotooncnnireportnotingtheratherslimswathoftheglobewhocouldseetheimpactoftheeclipseralstonsaidhewantedtoenabletherestoftheworldtoseehowclearitlookedtothoseofuswhowerefortunateenoughtoseeitthesliverofsunshinethentraveledsoutheastacrosscentralnevadasouthernutahandnorthernarizonaandthennewmexicoitpassedoveralbuquerquenewmexicoaboutseventhirtyfourpmninethirtyfourpmetbeforepeteringouteastoflubbocktexasaccordingtonasa";
//        int[] key2=new int[]{4};
//        String ciphertext=homework1.encOfCTC(plaintext,key);
//        String ciphertext2=homework1.encOfCTC(plaintext2,key2);
//        int[] frequency=homework1.frequencyCalculator(ciphertext,26,'a');
//        int[] frequency2=homework1.frequencyCalculator(ciphertext2,26,'a');
//        System.out.println(ciphertext2);
//        for (int i=0;i<26;i++){
//            System.out.println((char)('a'+i)+"="+frequency2[i]+",");
//        }
//        HashMap<Character,Integer> map=new HashMap<>();
//        String ciphertext3="#@.`+(@}%@*@`:=%!=:(~+?%:!?c+?&@?@(~%!@?c@:>?}>?=(:~+!@!*(.}?%:!:(&@*(.}?%:!}(:*@``+`%!^'@*%}c@(*+!}(:&>*@+!+'~:`?'%~%?'@``!>~_@(:=&%==@(@!?:>?}>?`/%?c&%==@(@!?#@.<+'>@`+'':/%!^`@*>(@*:~~>!%*+?%:!@<@!%=?c@*%}c@(%?`@'=%`#!:/!?:c:`?%'@?c%(&}+(?%@`%?~%^c?`>(}(%`@.:>?:#!:/?c+?+'~:`?+''*%}c@(`+(@}>_'%`c@&%!?c@`*%@!?%=%*}(@``:(%!`?+!&+(&`&:*>~@!?`c+<%!^?c@~+<+%'+_'@=:(/%&@`}(@+&`*(>?%!.+'':/`~+!.}@:}'@?:*c@*#?c+??c@.+(@`@*>(@+!&&:!:?*:!?+%!/@+#!@``@`/c%*c*:>'&_@@|}':%?@&?:*:~}(:~%`@?c@`@*>(%?.:=?c@&+?+@!*(.}?@&>`%!^?c+?*%}c@(+*:~}>?@(@!*(.}?%:!#@.%`!:?c%!^~:(@?c+!+`?(%!^:=_%?`/c@(@@+*c_%?*+!c+<@+<+'>@:=@%?c@( @(::(:!@?c@!>~_@(:=}:``%_'@<+'>@`=:(+#@.%``%~}'.?c@?:?+'!>~_@(:=<+'>@`?c+??c@#@.*+!c+<@`::>(:!@_%?':!^#@.*+!:!'.c+<@?/:}:``%_'@<+'>@` @(:+!&:!@%=/@*c::`@?:c+<@+?/:_%?#@.%?*:>'&c+<@:!@:==:>(}:``%_'@<+'>@` @(: @(: @(::!@:!@ @(:+!&:!@:!@%!=+*?@<@(.?%~@/@%!*(@+`@?c@'@!^?c:=?c@#@._.:!@_%?/@&:>_'@?c@!>~_@(:=}:``%_'@#@.``:+?c(@@_%?#@.c+`@%^c?}:``%_'@<+'>@` @(: @(: @(: @(: @(: @(: @(::!@ @(::!@ @(: @(::!@:!@:!@ @(: @(::!@ @(::!@:!@:!@ @(:+!&:!@:!@:!@:!@?c@?:?+'!>~_@(:=#@.`*+!_@/(%??@!%!`*%@!?%=%*=:(~+`?/:#@.'@!^?c`:+#@./%?c+'@!^?c:=@%^c?c+`?/@!?.@%^c??c+?%`?/:c>!&@(@&+!&=%=?.`%|<+'>@`_>?c:/':!^`c:>'&+#@._@c:/`c:(?%`?::`c:(??c@}(:_'@~/%?c`c:(?#@.``c:(?#@.`+(@<>'!@(+_'@?:/c+?%`#!:/!+`+_(>?@=:(*@+??+*#z>`?'%#@.:>'@+(!@&%!/@@#?/:+_:>?}+``/:(&`+_(>?@=:(*@+??+*#%`/c@(@+*:~}>?@(:(+!>~_@(:=*:~}>?@(`?(.@<@(.}:``%_'@<+'>@=:(+#@.>!?%'?c@.}(:&>*@(@*:^!%`+_'@}'+%!?@|?`%!*@*:~}>?@(`*+!/:(#?c(:>^c#@.<+'>@`@|?(@~@'.(+}%&'.#@.`~>`?_@`>==%*%@!?'.':!^?c+??c@.:==@(+<@(.'+(^@!>~_@(:=}:``%_'@<+'>@`#@.`~+._@#!:/!?:?c@>`@(%!?c@=:(~:=}+``/:(&`:(?c@.~+._@`?:(@&%!+*:~}>?@(`c+(&/+(@`>*c+`?c@&@*(.}?%:!#@.``?:(@&:!+&<&}'+.@(?c+?+'':/%??:}'+.?c@@!*(.}?@&&+?+`?:(@&:!?c@~:<%@&%`#:(?c@.*+!_@^@!@(+?@&_.+*:~}>?@(+`+!&/c@!?c@.+(@!@@&@&`>*c+`*:!&>*?%!^+`@*>(@?(+!`+*?%:!:!+`c:}}%!^`%?@!@|?.:>'''@+(!+_:>??c@#@.&%`?(%_>?%:!}(:_'@~`:>(*@c??}`///:}@!@&>";
//        homework1.frequencyCalculator(ciphertext3,map);
//        String order="+_*&@=^c%z#'~!:})(`?></|. ";
//        homework1.showFrequency(order,map);
//        HashMap<Character,Character> abiraryMap=new HashMap<>();
//        homework1.createArbitraryMapping(abiraryMap);
//        String plaintext3=homework1.decArbiraryMap(ciphertext3,abiraryMap);
//        System.out.println(ciphertext3);
//        System.out.println(plaintext3);
        question4();

    }
}

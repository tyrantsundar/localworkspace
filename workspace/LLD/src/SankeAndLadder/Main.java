package SankeAndLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {



    /**
     *
     *  SnakeOneStart = s1s
     *  SnakeOneEnd  = s1e
     *  LadderOneStart = l1s
     *  LadderOneEnd = l1e
     *
     *          s3s
     * 100	99	98	97	96	95	94	93	92	91
     *              l4e
     * 81	82	83	84	85	86	87	88	89	90
     *      s2s                 l3e
     * 80	79	78	77	76	75	74	73	72	71
     *          l4s                         s3e
     * 61	62	63	64	65	66	67	68	69	70
     *                          l1e
     * 60	59	58	57	56	55	54	53	52	51
     *      l2e                 s2e
     * 41	42	43	44	45	46	47	48	49	50
     *              l3s             s1s
     * 40	39	38	37	36	35	34	33	32	31
     *
     * 21	22	23	24	25	26	27	28	29	30
     *                          l2s
     * 20	19	18	17	16	15	14	13	12	11
     *          l1s         s1e
     * 1	2	3	4	5	6	7	8	9	10
     *
     * **/
    public static void main(String[] args) {
        //Ladder Positions
        int l1s = 3,l2s = 14, l3s = 37, l4s = 63;
        int l1e =  54,l2e =  42, l3e = 74, l4e = 84;

        //Snake Positions
        int s1e = 6, s2e = 47, s3e = 70;
        int s1s = 33, s2s = 79, s3s = 98;

        SpecialElement ladder1 = new Ladder(l1s,l1e);
        SpecialElement ladder2 = new Ladder(l2s,l2e);
        SpecialElement ladder3 = new Ladder(l3s,l3e);
        SpecialElement ladder4 = new Ladder(l4s,l4e);

        SpecialElement snake1 = new Snake(s1s,s1e);
        SpecialElement snake2 = new Snake(s2s,s1e);
        SpecialElement snake3 = new Snake(s3s,s3e);

        Set<SpecialElement> specialElementSet = new HashSet<>();
        specialElementSet.add(ladder1);
        specialElementSet.add(ladder2);
        specialElementSet.add(ladder3);
        specialElementSet.add(ladder4);
        specialElementSet.add(snake1);
        specialElementSet.add(snake2);
        specialElementSet.add(snake3);

        Player p1 = new Player("sundar");
        Player p2 = new Player("hari");
        List<Player> playerList = new ArrayList<>();
        playerList.add(p1);
        playerList.add(p2);


        Game game = new Game();
        game.setPlayers(playerList);
        game.getBoard().setSpecialElements(specialElementSet);
        game.gameOn();
    }
}

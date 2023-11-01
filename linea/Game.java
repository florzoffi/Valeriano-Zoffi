package linea;

public class Game {

    public static void main( String[] args) throws Exception {
        System.out.println( "Dimensions?");
        Line game = new Line( prompt( "Base? " ), prompt( "Height? " ), 'C' );
        System.out.println( game.show() );
    
      while ( !game.finished() ) {
          game.play( null, prompt( "Reds? " ) );
          System.out.println( game.show() );
      }
    
    }

    private static int prompt( String message ) {
        System.out.print( message );
        return Integer.parseInt( System.console().readLine() );
    }
}
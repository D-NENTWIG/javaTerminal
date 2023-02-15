grammar StdIn;

compileUnit
    :   seq EOF
    |   application EOF
    ;

application
    :   pipe
    |   call
    ;

pipe
    :   call1=call PIPE call2=call   #pipeBase
    |   pipe PIPE call               #pipeRecursive
    ;

seq
    :   seq SEMI application                      #seqRecursive
    |   cmd1=application SEMI cmd2=application        #seqBase
    ;

call    :   (NON_KEYWORD | LESS_THAN | GREATER_THAN | single_quotes | double_quotes | back_quotes | WHITE_SPACE)+;

single_quotes   :   '\'' single_quote_content '\'';
single_quote_content  :   (NON_KEYWORD | keyword | WHITE_SPACE | '"' | '`')*;

double_quotes   :   '"' double_quote_content '"';
double_quote_content  :   (NON_KEYWORD | keyword | WHITE_SPACE | '\'' | back_quotes)*;

back_quotes      :   '`' content = back_quote_content '`';
back_quote_content  :   (NON_KEYWORD | keyword | WHITE_SPACE | '"' | '\'')*;

keyword : SEMI | PIPE | GREATER_THAN | LESS_THAN;

WHITE_SPACE             : [ \t];
NON_KEYWORD    : ~[ \t"'`\n\r;|><];
SEMI        : ';';
PIPE        : '|';
GREATER_THAN          : '>';
LESS_THAN          : '<';
grammar Call;

compileUnit
    :   WHITE_SPACE* args WHITE_SPACE* EOF
    ;

args
    :   arg
    |   arg WHITE_SPACE+ left_args = args
    |   cmd = args WHITE_SPACE* io_operator = GREATER_THAN WHITE_SPACE* file = args
    |   io_operator = GREATER_THAN WHITE_SPACE* file = args WHITE_SPACE+ cmd = args
    |   cmd = args WHITE_SPACE* io_operator = LESS_THAN WHITE_SPACE* file = args
    |   io_operator = LESS_THAN WHITE_SPACE* file = args WHITE_SPACE+ cmd = args
    ;

arg
    :   no_quote = no_quotes arg?
    |   quoted arg?
    ;

no_quotes
    :   NON_KEYWORD+
    ;

quoted
    :   single_quotes
    |   double_quotes
    |   back_quotes
    ;

single_quotes   :   '\'' single_quote_content '\'';
single_quote_content  :   (NON_KEYWORD | keyword | WHITE_SPACE | '"' | '`')*;

double_quotes   :   '"' double_quote_content '"';
double_quote_content  :   content = (NON_KEYWORD | SEMI | PIPE | GREATER_THAN | LESS_THAN | WHITE_SPACE | '\'') double_quote_content
                |   back_quotes double_quote_content
                |  
                ;

back_quotes      :   '`' content = back_quote_content '`';
back_quote_content  :   (NON_KEYWORD | keyword | WHITE_SPACE | '"' | '\'')*;

keyword : SEMI | PIPE | GREATER_THAN | LESS_THAN;

WHITE_SPACE             : [ \t];
NON_KEYWORD    : ~[ \t"'`\n\r;|><];
SEMI           : ';';
PIPE           : '|';
GREATER_THAN             : '>';
LESS_THAN             : '<';
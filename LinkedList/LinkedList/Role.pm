package DataStructures::LinkedList::Role;

use strict;
use warnings FATAL => qw(all);
use Moose::Role;

requires(
    'insert_element_at_beginning',
    'insert_element_at_end',
    'insert_element',
    'delete_element_at_beginning',
    'delete_element_at_end',
    'delete_element',
    'get_element_in_position',

);

1;

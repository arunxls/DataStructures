package DataStructures::LinkedList::Single;
{
    use strict;
    use warnings FATAL => qw(all);

    use namespace::autoclean;

    use Moose;
    use MooseX::StrictConstructor;
    use Params::Validate qw(:all);
    use List::MoreUtils qw(none);

    extends 'DataStructures::LinkedList::AbstractLinkedList';

    use DataStructures::LinkedList::Node;

    with 'MooseX::Log::Log4perl';
    with 'DataStructures::LinkedList::Role';

    sub BUILD {
        my $self = shift;

        if ( scalar @{ $self->values } ) {

            #Set size of the LinkedList
            $self->size( scalar @{ $self->values } );

            my $_last_val = ${ $self->values }[-1];
            $self->log->debug( 'Constructing node with value: ', $_last_val );
            my $_prev_node = DataStructures::LinkedList::Node->new(
                'value'     => $_last_val,
                'next_node' => undef
            );

            foreach my $index ( reverse 0 .. $#{ $self->values } - 1 ) {
                $self->log->debug(
                    'Constructing node with value: ',
                    ${ $self->values }[$index]
                );

                $_prev_node = DataStructures::LinkedList::Node->new(
                    'value'     => ${ $self->values }[$index],
                    'next_node' => $_prev_node
                );
            }

            $self->_head_node($_prev_node);
            $self->log->debug( 'Head node has value: ',
                $self->_head_node->value );
        }
    }

    no Moose;

    __PACKAGE__->meta->make_immutable;

    sub insert_element {
        my $self = shift;
        my (%args) = validate(
            @_,
            {   value    => { type => SCALAR | UNDEF, default => undef },
                position => { type => SCALAR },
            }
        );

        my $position = $args{'position'};
        my $value    = $args{'value'};

        if ($position) {
            my $old_node
                = $self->get_element_in_position( 'position' => $position );

            my $new_node = DataStructures::LinkedList::Node->new(
                'value'     => $value,
                'next_node' => $old_node->next_node
            );

            $old_node->next_node($new_node);
            $self->size( $self->size + 1 );
        }
        else {
            my $new_node = DataStructures::LinkedList::Node->new(
                'value'     => $value,
                'next_node' => $self->_head_node
            );
            $self->_head_node($new_node);

            $self->size( $self->size + 1 );
        }
    }

    sub delete_element {
        my $self = shift;
        my (%args) = validate(
            @_,
            {   position => {
                    type      => SCALAR,
                    callbacks => {
                        'out_of_range' => sub {
                            $self->log->logdie("Invalid position number")
                                if ( $_[0] < 1 || $_[0] > $self->size );
                            return 1;
                            }
                    }
                },
            }
        );

        my $position = $args{'position'};
        if ( $position == 1 ) {

            $self->_head_node( $self->_head_node->next_node );
            $self->size( $self->size - 1 );
        }
        else {
            my $old_node = $self->get_element_in_position(
                'position' => $position - 1 );

            $old_node->next_node( $old_node->next_node->next_node );

            $self->size( $self->size - 1 );
        }
    }

    sub get_element_in_position {
        my $self = shift;

        my (%args) = validate(
            @_,
            {   position => {
                    type      => SCALAR,
                    callbacks => {
                        'out_of_range' => sub {
                            $self->log->logdie(
                                "The position $_[0] you are trying to access does not exist!"
                            ) if ( $_[0] > $self->size );
                            return 1;
                            }
                    }
                },
            }
        );

        my $position = $args{'position'};
        my $size     = $self->size;

        if ($size) {
            my $node      = $self->_head_node;
            my $next_node = $self->_head_node->next_node;
            my $index     = 1;
            until ( $index == $position ) {
                $node      = $next_node;
                $next_node = $node->next_node;
                $index++;
            }

            return $node;
        }
        else {
            return undef;
        }

    }


}

package DataStructures::LinkedList::AbstractLinkedList;
{
    use strict;
    use warnings;

    use namespace::autoclean;

    use Moose;
    use MooseX::StrictConstructor;
    use Params::Validate qw(:all);

=head1 ATTRIBUTES

=head2 C<values>

Optionally specify the initial values you want to store in the LinkedList.
Takes a arrayRef of the values as input. 

=cut

    has 'values' => (
        is      => 'ro',
        isa     => 'ArrayRef[Str]',
        default => sub { [] },
    );

# _head_node. This private attribute stores the head node for the linked list.
# Cannot be set on the constructor.

    has '_head_node' => (
        is       => 'rw',
        isa      => 'Maybe[DataStructures::LinkedList::Node]',
        init_arg => undef,
        lazy     => 1,
        default  => undef,
    );

    has 'size' => (
        is       => 'rw',
        isa      => 'Int',
        init_arg => undef,
        lazy     => 1,
        default  => 0,
    );

    no Moose;

    __PACKAGE__->meta->make_immutable;

    sub print_all_elements {
        my $self = shift;
        if ( defined $self->_head_node ) {
            $self->log->debug( "Head node has value : ",
                $self->_head_node->value );

            my $node = $self->_head_node;
            print $node->value;
            my $next_node = $self->_head_node->next_node;
            until ( !defined $next_node ) {
                $node = $next_node;
                print "->" . $node->value if defined($node);
                $next_node = $node->next_node;
            }
        }
        else {
            print "There are no elements in your LinkedList!";
        }
        print "\n";
    }

    sub insert_element_at_beginning {
        my $self = shift;
        my (%args) = validate( @_, { value => { type => SCALAR }, } );

        $self->insert_element( 'value' => $args{'value'}, 'position' => 0 );

    }

    sub insert_element_at_end {
        my $self = shift;
        my (%args) = validate( @_, { value => { type => SCALAR }, } );

        $self->insert_element(
            'value'    => $args{'value'},
            'position' => $self->size
        );
    }

    sub delete_element_at_beginning {
        my $self = shift;
        $self->delete_element( 'position' => 1 );
    }

    sub delete_element_at_end {
        my $self = shift;
        $self->delete_element( 'position' => $self->end );
    }

    sub get_value_in_position {
        my $self = shift;

        my $node = $self->get_element_in_position(@_);
        return $node->value();
    }

    sub set_value_in_position {
        my $self = shift;
        my (%args) = validate(
            @_,
            {   value    => { type => SCALAR | UNDEF, default => undef },
                position => { type => SCALAR },
            }
        );

        my $value = delete $args{'value'};

        my $node = $self->get_element_in_position(%args);
        $node->value($value);
    }
}

1;

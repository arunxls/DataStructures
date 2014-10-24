package DataStructures::LinkedList::Node;
{
    use strict;
    use warnings FATAL => qw(all);

    use namespace::autoclean;

    use Moose;
    use MooseX::StrictConstructor;

    with 'MooseX::Log::Log4perl';

=head1 ATTRIBUTES

=head2 C<value>

Value we want to store in the particular node. Can be undef

=cut

    has 'value' => (
        'is'  => 'rw',
        'isa' => 'Maybe[Str]',
    );

=head2 C<next_node>

Next node that we want to point to. Can be undef

=cut

    has 'next_node' => (
        'is'  => 'rw',
        'isa' => 'Maybe[DataStructures::LinkedList::Node]',
    );

    has 'prev_node' => (
        'is'  => 'rw',
        'isa' => 'Maybe[DataStructures::LinkedList::Node]',
    );

    sub BUILD {
        my $self = shift;

        $self->log->debug( 'Value: ', $self->value );
        $self->log->debug( 'Next Node value: ',
            $self->next_node ? $self->next_node->value : 'undef' );
    }

    no Moose;

    __PACKAGE__->meta->make_immutable;

}

1;

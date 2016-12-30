<?php
declare(strict_types = 1);
namespace Glagol\CompileClient\Socket\Response;

class WarningMessage extends AbstractMessage
{
    public function toString(): string
    {
        return '<warning>' . parent::toString() . '</warning>';
    }
}
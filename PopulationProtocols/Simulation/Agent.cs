using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    enum State : int { STATE_UNDEFINED=0,STATE_YES=1, STATE_NO=2 }
    enum InteracrionResult : int { INTER_NOTHING = 0, INTER_INC_YES = 1, INTER_INC_NO = 2, INTER_DEC = 3 }

    class Agent
    {
        public State S;

        public Agent() { }

        public Agent(int s)
        {
            this.S = (State)s;
        }

        public InteracrionResult Interact(Agent other)
        {
            if (this.S==State.STATE_YES)
            {
                if (other.S == State.STATE_YES)
                    return InteracrionResult.INTER_NOTHING;

                else if (other.S == State.STATE_NO)
                {
                    this.S = State.STATE_UNDEFINED;
                    other.S = State.STATE_UNDEFINED;
                    return InteracrionResult.INTER_DEC;
                }

                else
                {
                    other.S = State.STATE_YES;
                    return InteracrionResult.INTER_INC_YES;
                }
            }

            else if (this.S == State.STATE_NO)
            {
                if (other.S == State.STATE_YES)
                {
                    this.S = State.STATE_UNDEFINED;
                    other.S = State.STATE_UNDEFINED;
                    return InteracrionResult.INTER_DEC;
                }
                
                else if (other.S == State.STATE_NO)
                    return InteracrionResult.INTER_NOTHING;

                else
                {
                    other.S = State.STATE_NO;
                    return InteracrionResult.INTER_INC_NO;
                }
            }

            else
            {
                if (other.S == State.STATE_YES)
                {
                    this.S = State.STATE_YES;
                    return InteracrionResult.INTER_INC_YES;
                }

                else if (other.S == State.STATE_NO)
                {
                    this.S = State.STATE_NO;
                    return InteracrionResult.INTER_INC_NO;
                }
                else
                    return InteracrionResult.INTER_NOTHING;
            }
        }

        public override string ToString()
        {
            switch(this.S)
            {
                case State.STATE_UNDEFINED: return "u";
                case State.STATE_YES: return "y";
                case State.STATE_NO: return "n";
                default: return "error"; 
            }
        }
    }
}

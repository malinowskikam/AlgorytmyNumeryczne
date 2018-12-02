using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class SimulationControl
    {
        readonly Random gen;
        public Agent[] Agents;
        public int YesCount;
        public int NoCount;
        public int UndefinedCount;
        readonly public int NOfAgents;

        public SimulationControl(int n, Random gen)
        {
            this.NOfAgents = n;
            this.Agents = new Agent[n];
            for (int i = 0; i < n; i++)
                Agents[i] = new Agent();

            this.gen = gen;
        }

        public double GetProbability(int yesc, int noc, int repeats=1000000,int safeswitch=100000)
        {
            int succesCount = 0;
            for (int i = 0; i < repeats; i++)
            {
                PrepareSimulation(yesc, noc);
                if (PerformSimulation(safeswitch))
                    succesCount++;
            };
            return ((double)succesCount)/repeats;
        }

        public void PrepareSimulation(int yesc, int noc)
        {
            this.YesCount = yesc;
            this.NoCount = noc;
            this.UndefinedCount = NOfAgents - yesc - noc;

            for (int i = 0; i < YesCount; i++)
                Agents[i].S = State.STATE_YES;

            for (int i = YesCount; i < NOfAgents - UndefinedCount; i++)
                Agents[i].S = State.STATE_NO;

            for (int i = NOfAgents - UndefinedCount; i < NOfAgents; i++)
                Agents[i].S = State.STATE_UNDEFINED;
        }

        public Boolean PerformSimulation(int safeswitch)
        {
            if (YesCount == NOfAgents)
                return true;
            if (NoCount == NOfAgents)
                return false;
            if (UndefinedCount == NOfAgents)
                return false;

            for (int i = 0; i < safeswitch; i++)
            {
                switch(Agents[gen.Next(NOfAgents)].Interact(Agents[gen.Next(NOfAgents)]))
                {
                    case InteracrionResult.INTER_DEC : { YesCount--; NoCount--; break; }
                    case InteracrionResult.INTER_INC_YES: { YesCount++; break; }
                    case InteracrionResult.INTER_INC_NO: { NoCount++; break; }
                }

                if(YesCount == NOfAgents)
                    return true;
                if (NoCount == NOfAgents)
                    return false;
                if (UndefinedCount == NOfAgents)
                    return false;
            }

            return false;
        }

    }
}

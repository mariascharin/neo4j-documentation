/*
 * Copyright (c) 2002-2019 "Neo4j,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.doc.server;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import org.neo4j.doc.server.rest.AbstractRestFunctionalTestBase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NeoServerDocIT extends AbstractRestFunctionalTestBase
{
    @Test
    public void whenServerIsStartedItshouldStartASingleDatabase() throws Exception
    {
        assertNotNull( server().getDatabase() );
    }

    @Test
    public void shouldRedirectRootToBrowser() throws Exception
    {
        assertFalse( server().baseUri()
                .toString()
                .contains( "browser" ) );

        HTTP.Response res = HTTP.withHeaders( HttpHeaders.ACCEPT, MediaType.TEXT_HTML ).GET( server().baseUri().toString() );
        assertThat( res.header( "Location" ), containsString( "browser") );
    }
}
